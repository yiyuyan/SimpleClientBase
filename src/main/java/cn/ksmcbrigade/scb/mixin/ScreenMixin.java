package cn.ksmcbrigade.scb.mixin;

import cn.ksmcbrigade.scb.SimpleClientBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(method = "tick",at = @At("HEAD"))
    public void tick(CallbackInfo ci) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.screenTick(MC);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Inject(method = "keyPressed",at = @At("HEAD"))
    public void key(int key, int p_96553_, int p_96554_, CallbackInfoReturnable<Boolean> cir) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.keyInput(key,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
