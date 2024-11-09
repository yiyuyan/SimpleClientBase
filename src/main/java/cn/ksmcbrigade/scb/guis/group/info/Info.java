package cn.ksmcbrigade.scb.guis.group.info;

import cn.ksmcbrigade.scb.SimpleClientBase;
import cn.ksmcbrigade.scb.module.Groups;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class Info {
    public boolean onGroup = true;
    public final CurGroupInfo groupInfo;

    public Info(CurGroupInfo groupInfo){
        this.groupInfo = groupInfo;
    }

    public void onGroup(boolean onGroup){
        this.onGroup = onGroup;
    }

    public void last(){
        if(this.onGroup){
            this.groupInfo.last();
        }
        else{
            this.groupInfo.cur.featureList.renderer.last();
        }
    }

    public void next(){
        if(this.onGroup){
            this.groupInfo.next();
        }
        else{
            this.groupInfo.cur.featureList.renderer.next();
        }
    }

    public void render(GuiGraphics context, Font textRenderer){
        Groups.COMBAT.render(context,textRenderer);
        Groups.BLOCK.render(context,textRenderer);
        Groups.ITEM.render(context,textRenderer);
        Groups.RENDER.render(context,textRenderer);
        Groups.MISC.render(context,textRenderer);
        if(!this.onGroup){
            if(this.groupInfo.cur.features.isEmpty() && SimpleClientBase.checkGroupEmpty(this.groupInfo.cur)){
                System.out.println("empty:"+this.groupInfo.cur);
            }
            this.groupInfo.cur.featureList.render(context, textRenderer);

        }
    }
}
