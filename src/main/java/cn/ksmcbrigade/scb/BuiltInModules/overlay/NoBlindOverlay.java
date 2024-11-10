package cn.ksmcbrigade.scb.BuiltInModules.overlay;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.misc.CheckHasEffectEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffects;

public class NoBlindOverlay extends Module {
    public NoBlindOverlay() {
        super(NoBlindOverlay.class.getSimpleName(),ModuleType.OVERLAY);
    }

    @Override
    public void hasEffect(Minecraft mc, CheckHasEffectEvent event) {
        if(event.effect.equals(MobEffects.BLINDNESS) || event.effect.equals(MobEffects.DARKNESS)){
            event.has = false;
        }
    }
}
