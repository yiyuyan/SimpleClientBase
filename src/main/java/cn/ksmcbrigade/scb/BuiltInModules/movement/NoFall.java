package cn.ksmcbrigade.scb.BuiltInModules.movement;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class NoFall extends Module {
    public NoFall() {
        super("NoFall",ModuleType.MOVEMENT);
    }

    @Override
    public void playerTick(Minecraft MC, @Nullable Player player) {
        if(player!=null && player.fallDistance>=3 && MC.getConnection()!=null){
            MC.getConnection().getConnection().send(new ServerboundMovePlayerPacket.StatusOnly(true));
        }
    }
}
