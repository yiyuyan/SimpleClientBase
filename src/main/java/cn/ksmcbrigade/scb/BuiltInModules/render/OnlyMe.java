package cn.ksmcbrigade.scb.BuiltInModules.render;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.render.RenderEntityEvent;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;

public class OnlyMe extends Module {

    public boolean render;

    public OnlyMe() {
        super(OnlyMe.class.getSimpleName(),ModuleType.RENDER);
    }

    public static JsonObject get(){
        JsonObject object = new JsonObject();
        object.addProperty("type","Living");
        return object;
    }

    @Override
    public void renderEntity(Minecraft mc, RenderEntityEvent event) {
        if(Minecraft.getInstance().player!=null && event.entity.getId()!=Minecraft.getInstance().player.getId()){
            event.setCanceled(true);
        }
    }
}
