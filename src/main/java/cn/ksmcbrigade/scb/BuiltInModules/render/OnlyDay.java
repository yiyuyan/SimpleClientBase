package cn.ksmcbrigade.scb.BuiltInModules.render;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.render.DayTimeEvent;
import net.minecraft.client.Minecraft;

public class OnlyDay extends Module {
    public OnlyDay() {
        super(OnlyDay.class.getSimpleName(),ModuleType.RENDER);
    }

    @Override
    public void dayTime(Minecraft MC, DayTimeEvent event) throws Exception {
        event.time = 1000L;
    }
}
