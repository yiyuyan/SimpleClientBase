package cn.ksmcbrigade.scb.BuiltInModules;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.FluidTypeInCameraEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FogType;
import org.jetbrains.annotations.Nullable;

public class NoUnderOverlay extends Module {
    public NoUnderOverlay() {
        super(NoUnderOverlay.class.getSimpleName(),ModuleType.RENDER);
    }

    @Override
    public void fluidInCameraEvent(Minecraft MC, FluidTypeInCameraEvent event) {
        event.value = FogType.NONE;
    }
}
