package cn.ksmcbrigade.scb.BuiltInModules.overlay;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.render.FluidTypeInCameraEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.material.FogType;

public class NoUnderOverlay extends Module {
    public NoUnderOverlay() {
        super(NoUnderOverlay.class.getSimpleName(),ModuleType.OVERLAY);
    }

    @Override
    public void fluidInCameraEvent(Minecraft MC, FluidTypeInCameraEvent event) {
        event.value = FogType.NONE;
    }
}
