package cn.ksmcbrigade.scb.commands;

import cn.ksmcbrigade.scb.command.Command;
import cn.ksmcbrigade.scb.uitls.CommandUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Help extends Command {
    public Help() {
        super("help");
    }

    @Override
    public void onCommand(Minecraft MC, Player player, String[] args) {
        if(player!=null){
            CommandUtils.getAll().forEach(c -> player.sendSystemMessage(Component.nullToEmpty("command: "+c.name+"     args: "+c.length)));
        }
    }
}
