package cn.ksmcbrigade.scb.mixin;

import cn.ksmcbrigade.scb.guis.alt.manager.AltManagerGui;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

    @Inject(method = "init",at = @At("TAIL"))
    public void init(CallbackInfo ci){
        Window window = Minecraft.getInstance().getWindow();
        this.addRenderableWidget(Button.builder(Component.literal("AltManager"),(on)-> Minecraft.getInstance().setScreen(new AltManagerGui(new TitleScreen()))).bounds(window.getGuiScaledWidth()-50-2,2,50,25).build());
    }
}
