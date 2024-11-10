package cn.ksmcbrigade.scb.BuiltInModules.overlay;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.misc.CheckHasEffectEvent;
import cn.ksmcbrigade.scb.module.events.render.RenderFireEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffects;

public class NoFireOverlay extends Module {
    public NoFireOverlay() {
        super(NoFireOverlay.class.getSimpleName(),ModuleType.OVERLAY);
    }

    @Override
    public void renderFire(Minecraft mc, RenderFireEvent event) {
        event.setCanceled(true);
    }
}
