package cn.ksmcbrigade.scb.mixin.misc;

import cn.ksmcbrigade.scb.SimpleClientBase;
import cn.ksmcbrigade.scb.module.events.misc.TimerEvent;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "close",at = @At("HEAD"))
    public void close(CallbackInfo ci) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.MCClose(MC);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Inject(method = "crash",at = @At("HEAD"))
    private static void crash(CallbackInfo ci) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.MCClose(MC);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Inject(method = {"getTickTargetMillis"},at = @At("RETURN"),cancellable = true)
    public void event(float p_308953_, CallbackInfoReturnable<Float> cir){
        TimerEvent event = new TimerEvent(cir.getReturnValue());
        NeoForge.EVENT_BUS.post(event);
        cir.setReturnValue(event.tickTargetMillis);
    }
}
