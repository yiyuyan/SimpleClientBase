package cn.ksmcbrigade.scb.BuiltInModules.overlay;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.render.RenderOverlayEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;

public class NoPumpkinOverlay extends Module {

    public static ResourceLocation resourceLocation = ResourceLocation.withDefaultNamespace("textures/misc/pumpkinblur.png");

    public NoPumpkinOverlay() throws IOException {
        super(NoPumpkinOverlay.class.getSimpleName(),ModuleType.OVERLAY);
    }

    @Override
    public void renderTexOverlayEvent(Minecraft MC, RenderOverlayEvent event) throws Exception {
        if(event.resourceLocation.equals(resourceLocation)){
            event.setCanceled(true);
        }
    }
}
