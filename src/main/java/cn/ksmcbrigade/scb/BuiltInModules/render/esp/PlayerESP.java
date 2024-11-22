package cn.ksmcbrigade.scb.BuiltInModules.render.esp;

import cn.ksmcbrigade.scb.module.Config;
import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.uitls.RenderUtils;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerESP extends Module {

    public boolean render;
    public boolean set = false;
    public ArrayList<BlockPos> poses = new ArrayList<>();

    public PlayerESP() throws IOException {
        super(PlayerESP.class.getSimpleName(),false,-1,new Config(new File(PlayerESP.class.getSimpleName()),get()),ModuleType.RENDER);
    }

    @Override
    public void enabled(Minecraft MC) throws Exception {
        getConfig().reload();
        this.poses.clear();
        set = false;
    }

    public static JsonObject get(){
        JsonObject object = new JsonObject();
        object.addProperty("blockSleeping",false);
        object.addProperty("blockFlight",false);
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

        if(Minecraft.getInstance().player==null){
            return;
        }
        for (Player player:Minecraft.getInstance().level.players()) {
            if(player.getId()==Minecraft.getInstance().player.getId()){
                return;
            }
            if(player.isSleeping() && getConfig().get("blockSleeping").getAsBoolean()){
                continue;
            }
            if((player.isFallFlying() || player.getAbilities().flying) && getConfig().get("blockFlight").getAsBoolean()){
                continue;
            }
            RenderUtils.renderPlayer(event.getPoseStack(),event.getModelViewMatrix(),event.getProjectionMatrix(),player.getPosition(0).add(-0.25,0,-0.25),player,this.getConfig().get("red").getAsFloat()/255f,this.getConfig().get("green").getAsFloat()/255f,this.getConfig().get("blue").getAsFloat()/255f,this.getConfig().get("opacity").getAsFloat());
        }
    }
}