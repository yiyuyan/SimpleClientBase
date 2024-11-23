package cn.ksmcbrigade.scb.BuiltInModules.render.esp;

import cn.ksmcbrigade.scb.module.Config;
import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.uitls.RenderUtils;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectileESP extends Module {

    public boolean render;
    public boolean set = false;
    public ArrayList<BlockPos> poses = new ArrayList<>();

    public ProjectileESP() throws IOException {
        super(ProjectileESP.class.getSimpleName(),false,-1,new Config(new File(ProjectileESP.class.getSimpleName()),get()),ModuleType.RENDER);
    }

    @Override
    public void enabled(Minecraft MC) throws Exception {
        getConfig().reload();
        this.poses.clear();
        set = false;
    }

    public static JsonObject get(){
        JsonObject object = new JsonObject();
        object.addProperty("blockWitherSkulls",false);
        object.addProperty("blockFireBalls",false);
        object.addProperty("blockDragonBalls",false);
        object.addProperty("blockWindCharges",false);
        object.addProperty("blockSnowBalls",true);
        object.addProperty("red",173F);
        object.addProperty("green",216F);
        object.addProperty("blue",230F);
        object.addProperty("opacity",1F);
        return object;
    }

    @Override
    public void renderLevel(Minecraft mc, RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            return;
        }

        if(Minecraft.getInstance().player==null) return;
        for (Projectile entity:Minecraft.getInstance().level.getEntitiesOfClass(Projectile.class,new AABB(Minecraft.getInstance().player.getPosition(0),Minecraft.getInstance().player.getPosition(0)).inflate(Minecraft.getInstance().options.getEffectiveRenderDistance()*16))) {
            if(entity instanceof WitherSkull && getConfig().get("blockWitherSkulls").getAsBoolean()){
                continue;
            }
            if(entity instanceof Fireball && getConfig().get("blockFireBalls").getAsBoolean()){
                continue;
            }
            if(entity instanceof DragonFireball && getConfig().get("blockDragonBalls").getAsBoolean()){
                continue;
            }
            if(entity instanceof WindCharge && getConfig().get("blockWindCharges").getAsBoolean()){
                continue;
            }
            if(entity instanceof Snowball && getConfig().get("blockSnowBalls").getAsBoolean()){
                continue;
            }
            RenderUtils.renderPlayer(event.getPoseStack(),event.getModelViewMatrix(),event.getProjectionMatrix(),entity.getPosition(0).add(-0.25,0,-0.25),entity,this.getConfig().get("red").getAsFloat()/255f,this.getConfig().get("green").getAsFloat()/255f,this.getConfig().get("blue").getAsFloat()/255f,this.getConfig().get("opacity").getAsFloat());
        }
    }
}
