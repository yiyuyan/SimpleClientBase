package cn.ksmcbrigade.scb.BuiltInModules.render;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.misc.GetOptionValueEvent;
import net.minecraft.client.Minecraft;

import java.awt.event.KeyEvent;

public class FullBright extends Module {
    public FullBright() {
        super(FullBright.class.getSimpleName(),false, KeyEvent.VK_C,ModuleType.RENDER);
    }

    @Override
    public void getOptionInstanceEvent(Minecraft MC, GetOptionValueEvent event) {
        if(event.cap.equals(MC.options.gamma().toString())){
            event.value = 300.0D;
        }
    }
}
