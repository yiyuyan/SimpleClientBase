package cn.ksmcbrigade.scb.module.events.render;

import net.minecraft.world.level.material.FogType;
import net.neoforged.bus.api.Event;

public class FluidTypeInCameraEvent extends Event {
    public FogType value;

    public FluidTypeInCameraEvent(FogType value){
        this.value = value;
    }
}
