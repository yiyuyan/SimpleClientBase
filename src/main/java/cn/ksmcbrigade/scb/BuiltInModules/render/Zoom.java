package cn.ksmcbrigade.scb.BuiltInModules.render;

import cn.ksmcbrigade.scb.module.Config;
import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.render.ZoomEvent;
import cn.ksmcbrigade.scb.uitls.ClientRegistry;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;

public class Zoom extends Module {

    public final KeyMapping keyMapping = new KeyMapping("Zoom", InputConstants.KEY_B,KeyMapping.CATEGORY_GAMEPLAY);

    public Zoom() throws IOException {
        super(Zoom.class.getSimpleName(),false,-1,new Config(new File(Zoom.class.getSimpleName()),get()),ModuleType.RENDER);
        ClientRegistry.registerKeyBinding(this.keyMapping);
    }

    public static JsonObject get(){
        JsonObject object = new JsonObject();
        object.addProperty("fov",9D);
        return object;
    }

    @Override
    public void zoomEvent(Minecraft MC, ZoomEvent event) throws Exception {
        if(keyMapping.isDown()){
            event.foV = event.foV / this.getConfig().get("fov").getAsDouble();
        }
    }
}
