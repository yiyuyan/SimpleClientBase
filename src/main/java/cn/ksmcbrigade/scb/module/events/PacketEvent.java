package cn.ksmcbrigade.scb.module.events;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import org.spongepowered.asm.mixin.injection.callback.Cancellable;

public class PacketEvent extends Event implements ICancellableEvent {
    public Packet<?> packet;

    public PacketEvent(Packet<?> packet){
        this.packet = packet;
    }
}
