package cn.ksmcbrigade.scb.BuiltInModules;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.DayTimeEvent;
import cn.ksmcbrigade.scb.module.events.FluidTypeInCameraEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.material.FogType;

public class OnlyDay extends Module {
    public OnlyDay() {
        super(OnlyDay.class.getSimpleName(),ModuleType.RENDER);
    }

    @Override
    public void dayTime(Minecraft MC, DayTimeEvent event) throws Exception {
        event.time = 1000L;
    }
}
