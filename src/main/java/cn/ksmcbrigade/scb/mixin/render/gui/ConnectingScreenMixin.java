package cn.ksmcbrigade.scb.mixin.render.gui;

import cn.ksmcbrigade.scb.config.TempConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.TransferState;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConnectScreen.class)
public class ConnectingScreenMixin {
    @Inject(method = "connect",at = @At("HEAD"))
    public void connectOn(Minecraft p_251955_, ServerAddress p_249536_, ServerData p_252078_, TransferState p_320415_, CallbackInfo ci){
       TempConfig.lastIp = p_249536_.toString();
    }
}
