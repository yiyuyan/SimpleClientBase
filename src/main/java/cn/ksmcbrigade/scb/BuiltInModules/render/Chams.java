package cn.ksmcbrigade.scb.BuiltInModules.render;

import cn.ksmcbrigade.scb.module.Config;
import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.misc.GetOptionValueEvent;
import cn.ksmcbrigade.scb.module.events.render.RenderEntityPreEvent;
import cn.ksmcbrigade.scb.module.events.render.RenderedEntityEvent;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.opengl.GL11;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Chams extends Module {

    public boolean render;

    public Chams() throws IOException {
        super(Chams.class.getSimpleName(),false,-1,new Config(new File("Chams"),get()),ModuleType.RENDER);
    }

    public static JsonObject get(){
        JsonObject object = new JsonObject();
        object.addProperty("type","Living");
        return object;
    }

    @Override
    public void preRenderEntity(Minecraft mc, RenderEntityPreEvent event) throws Exception {
        render = switch (this.getConfig().get("type").getAsString()){
            case "Player" -> event.entity instanceof Player;
            case "Living" -> event.entity instanceof LivingEntity;
            case null, default -> true;
        };
        if(render){
            GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
            GL11.glPolygonOffset(1.0f,-1000000F);
        }
    }

    @Override
    public void renderedEntity(Minecraft mc, RenderedEntityEvent event) throws Exception {
        if(render){
            GL11.glPolygonOffset(1.0f,1000000F);
            GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
        }
        render = false;
    }
}
