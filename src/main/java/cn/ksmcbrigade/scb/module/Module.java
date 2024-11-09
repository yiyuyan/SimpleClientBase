package cn.ksmcbrigade.scb.module;

import cn.ksmcbrigade.scb.BuiltInModules.HUD;
import cn.ksmcbrigade.scb.module.events.*;
import cn.ksmcbrigade.scb.uitls.ModuleUtils;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.io.File;

public class Module {
    public final String name;
    public boolean enabled = false;
    public int key = -1;
    private Config config;

    public ModuleType type;

    public Module(String name, boolean enabled, int key, @Nullable Config config,boolean nameConfig,ModuleType moduleType){
        this.name = name;
        this.enabled = enabled;
        this.key = key;
        this.config = config;
        this.type = moduleType;
        if(config!=null && nameConfig){
            this.config.file = new File(this.name);
        }
        boolean check = ModuleType.check();
        if(check){
            for (ModuleType value : ModuleType.values()) {
                if(value.name.equalsIgnoreCase(this.type.name)){
                    this.type = value;
                }
            }
        }
        this.type.group.add(this);
    }

    public Module(String name, boolean enabled, int key, Config config,ModuleType moduleType){
        this(name,enabled,key,config,true,moduleType);
    }

    public Module(String name, boolean enabled, int key,ModuleType moduleType){
        this(name,enabled,key,null,false,moduleType);
    }

    public Module(String name, boolean enabled,ModuleType moduleType){
        this(name,enabled,-1,null,false,moduleType);
    }

    public Module(String name,ModuleType moduleType){
        this(name,false,-1,null,false,moduleType);
    }

    public void setEnabled(boolean enabled) throws Exception {
        this.enabled = enabled;
        Minecraft MC = Minecraft.getInstance();
        if(enabled){
            enabled(MC);
        }
        else{
            disabled(MC);
        }
        ModuleUtils.save();
    }

    public String getName(){return I18n.get(this.name);}

    public int getKey() {return key;}

    public void setKey(int key) {this.key = key;}

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public int length(){
        return getName().length();
    }

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", enabled=" + enabled +
                ", key=" + key +
                ", config=" + config +
                '}';
    }

    public void enabled(Minecraft MC) throws Exception{}

    public void render() throws Exception{}
    public void renderGame(GuiGraphics pose) throws Exception{}
    public void renderGameMix(GuiGraphics pose) throws Exception{}

    public void keyInput(int key, boolean screen) throws Exception{}

    public JsonObject screenshot(JsonObject data) throws Exception{return data;}

    public void clientTick(Minecraft MC) throws Exception{}
    public void clientPostTick(Minecraft MC) throws Exception{}
    public void worldTick(Minecraft MC,@Nullable Level world) throws Exception{}
    public void worldPostTick(Minecraft MC,@Nullable Level world) throws Exception{}
    public void playerTick(Minecraft MC,@Nullable Player player) throws Exception{}
    public void playerPostTick(Minecraft MC,@Nullable Player player) throws Exception{}
    public void screenTick(Minecraft MC) throws Exception{}

    public void getOptionInstanceEvent(Minecraft MC,GetOptionValueEvent event) throws Exception{}

    public void fluidInCameraEvent(Minecraft MC,FluidTypeInCameraEvent event) throws Exception{}

    public void blockShape(Minecraft MC,BlockShapeEvent event) throws Exception{}
    public void renderBlockEvent(Minecraft MC,RenderBlockEvent event) throws Exception{}

    public void packetEvent(Minecraft MC, PacketEvent event) throws Exception{}

    public void zoomEvent(Minecraft MC,ZoomEvent event) throws Exception{}
    public void renderTexOverlayEvent(Minecraft MC,RenderOverlayEvent event) throws Exception{}
    public void dayTime(Minecraft MC,DayTimeEvent event) throws Exception{}

    public void timerChange(Minecraft MC,TimerEvent event) throws Exception{}

    public void disabled(Minecraft MC) throws Exception{}

    public void MCClose(Minecraft MC) throws Exception{}
}
