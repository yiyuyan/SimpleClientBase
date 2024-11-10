package cn.ksmcbrigade.scb.mixin.render.gui;

import cn.ksmcbrigade.scb.config.TempConfig;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(JoinMultiplayerScreen.class)
public abstract class ServersListScreenMixin extends Screen {
    protected ServersListScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

    @Inject(method = "init",at = @At("TAIL"))
    public void init(CallbackInfo ci){
        if(this.minecraft==null) return;
        Button button = Button.builder(Component.literal("LastServer"), ref-> {
            if(this.minecraft!=null){
                ConnectScreen.startConnecting(this, this.minecraft, ServerAddress.parseString(TempConfig.lastIp), new ServerData("",TempConfig.lastIp, ServerData.Type.OTHER), false,null);
            }
        }).size(100,20).pos(this.minecraft.getWindow().getGuiScaledWidth()-154,10).build();
        button.active = !TempConfig.lastIp.isEmpty();
        this.addRenderableWidget(button);
    }
}
