package cn.ksmcbrigade.scb.BuiltInModules.render;

import cn.ksmcbrigade.scb.config.HUDConfig;
import cn.ksmcbrigade.scb.guis.group.info.CurGroupInfo;
import cn.ksmcbrigade.scb.guis.group.info.Info;
import cn.ksmcbrigade.scb.module.Groups;
import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import static cn.ksmcbrigade.scb.config.HUDConfig.init;

public class HUD extends Module {


    public final Info info;

    public HUD() {
        super("HUD",true, ModuleType.RENDER);
        init = false;
        init();
        info = new Info(new CurGroupInfo(Groups.COMBAT));
    }

    @Override
    public void renderGameMix(GuiGraphics pose) {
        if(info.groupInfo.cur==null){
            System.out.println("what fuck?");
            info.groupInfo.cur(Groups.COMBAT);
        }
        info.render(pose, Minecraft.getInstance().font);
    }

    @Override
    public void keyInput(int key, boolean screen) throws Exception {
        if(HUDConfig.UP.isDown()){
            info.last();
        }
        if(HUDConfig.DOWN.isDown()){
            info.next();
        }

        if(HUDConfig.LEFT.isDown()){
            info.onGroup(true);
        }
        if(HUDConfig.RIGHT.isDown()){
            info.onGroup(false);
        }
        if(HUDConfig.SET.isDown()){
            info.groupInfo.cur.featureList.renderer.cur.feature.setEnabled(!info.groupInfo.cur.featureList.renderer.cur.feature.enabled);
        }
    }
}
