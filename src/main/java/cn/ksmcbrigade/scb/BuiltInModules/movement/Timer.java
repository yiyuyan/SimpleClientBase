package cn.ksmcbrigade.scb.BuiltInModules.movement;

import cn.ksmcbrigade.scb.module.Config;
import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.module.events.misc.TimerEvent;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;

public class Timer extends Module {

    public Timer() throws IOException {
        super(Timer.class.getSimpleName(),false,-1,new Config(new File(Timer.class.getSimpleName()),get()),ModuleType.MOVEMENT);
    }

    public static JsonObject get(){
        JsonObject object = new JsonObject();
        object.addProperty("timer",1.0F);
        return object;
    }

    @Override
    public void enabled(Minecraft MC) throws Exception {
        this.getConfig().reload();
    }

    @Override
    public void timerChange(Minecraft MC, TimerEvent event) {
        event.tickTargetMillis/=this.getConfig().get("timer").getAsFloat();
    }
}
