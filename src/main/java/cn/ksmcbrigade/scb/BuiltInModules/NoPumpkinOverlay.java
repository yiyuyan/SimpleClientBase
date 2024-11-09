package cn.ksmcbrigade.scb.BuiltInModules;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.RenderOverlayEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;

public class NoPumpkinOverlay extends Module {

    public static ResourceLocation resourceLocation = ResourceLocation.withDefaultNamespace("textures/misc/pumpkinblur.png");

    public NoPumpkinOverlay() throws IOException {
        super(NoPumpkinOverlay.class.getSimpleName(),ModuleType.RENDER);
    }

    @Override
    public void renderTexOverlayEvent(Minecraft MC, RenderOverlayEvent event) throws Exception {
        if(event.resourceLocation.equals(resourceLocation)){
            event.setCanceled(true);
        }
    }
}
