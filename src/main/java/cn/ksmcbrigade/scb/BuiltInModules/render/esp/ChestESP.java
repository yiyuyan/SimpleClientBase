package cn.ksmcbrigade.scb.BuiltInModules.render.esp;

import cn.ksmcbrigade.scb.module.Config;
import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.uitls.ChunkUtils;
import cn.ksmcbrigade.scb.uitls.RenderUtils;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChestESP extends Module {

    public boolean render;
    public boolean set = false;
    public ArrayList<BlockPos> poses = new ArrayList<>();

    public ChestESP() throws IOException {
        super(ChestESP.class.getSimpleName(),false,-1,new Config(new File("ChestESP"),get()),ModuleType.RENDER);
    }

    @Override
    public void enabled(Minecraft MC) throws Exception {
        getConfig().reload();
        this.poses.clear();
        set = false;
    }

    public static JsonObject get(){
        JsonObject object = new JsonObject();
        object.addProperty("blockChest",false);
        object.addProperty("blockShulker",false);
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

        for (BlockEntity state : ChunkUtils.getLoadedBlockEntities().toList()) {
            if(!(state instanceof ChestBlockEntity) && !(state instanceof ShulkerBoxBlockEntity)){
                continue;
            }
            if((state instanceof ChestBlockEntity) && getConfig().get("blockChest").getAsBoolean()){
                continue;
            }
            if((state instanceof ShulkerBoxBlockEntity) && getConfig().get("blockShulker").getAsBoolean()){
                continue;
            }
            RenderUtils.renderBlock(event.getPoseStack(),event.getModelViewMatrix(),event.getProjectionMatrix(),state.getBlockPos(),this.getConfig().get("red").getAsFloat()/255f,this.getConfig().get("green").getAsFloat()/255f,this.getConfig().get("blue").getAsFloat()/255f,this.getConfig().get("opacity").getAsFloat(),1f);
        }
    }
}
