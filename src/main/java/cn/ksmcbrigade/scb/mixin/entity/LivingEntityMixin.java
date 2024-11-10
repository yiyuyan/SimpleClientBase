package cn.ksmcbrigade.scb.mixin.entity;

import cn.ksmcbrigade.scb.module.events.misc.CheckHasEffectEvent;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "hasEffect",at = @At("RETURN"), cancellable = true)
    public void has(Holder<MobEffect> p_316430_, CallbackInfoReturnable<Boolean> cir){
        CheckHasEffectEvent event = new CheckHasEffectEvent(p_316430_,cir.getReturnValue());
        NeoForge.EVENT_BUS.post(event);
        cir.setReturnValue(event.has);
    }

    @Inject(method = "getEffect",at = @At("RETURN"), cancellable = true)
    public void get(Holder<MobEffect> p_316430_, CallbackInfoReturnable<MobEffectInstance> cir){
        CheckHasEffectEvent event = new CheckHasEffectEvent(p_316430_,cir.getReturnValue()!=null);
        NeoForge.EVENT_BUS.post(event);
        if(!event.has){
            cir.setReturnValue(null);
        }
    }
}
