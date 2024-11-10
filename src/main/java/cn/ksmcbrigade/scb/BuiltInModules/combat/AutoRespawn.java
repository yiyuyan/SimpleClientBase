package cn.ksmcbrigade.scb.BuiltInModules.combat;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn",ModuleType.COMBAT);
    }

    @Override
    public void playerTick(Minecraft MC, @Nullable Player player) throws Exception {
        if(player!=null && !player.isAlive() && MC.getConnection()!=null){
            MC.getConnection().getConnection().send(new ServerboundClientCommandPacket(ServerboundClientCommandPacket.Action.PERFORM_RESPAWN));
        }
    }
}
