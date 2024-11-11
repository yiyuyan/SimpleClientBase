package cn.ksmcbrigade.scb.mixin.render.gui;

import cn.ksmcbrigade.scb.SimpleClientBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public class ScreenMixin {

    @Unique
    private final ResourceLocation simpleClientBase$logo = ResourceLocation.tryBuild("scb","logo.png");

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

    @Inject(method = "renderBackground",at = @At("TAIL"))
    public void render(GuiGraphics p_281549_, int p_281550_, int p_282878_, float p_282465_, CallbackInfo ci){
        if (simpleClientBase$logo != null) {
            p_281549_.blit(simpleClientBase$logo,p_281549_.guiWidth()-65,p_281549_.guiHeight()-50,0,0,65,50,65,50);
        }
    }
}
