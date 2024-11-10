package cn.ksmcbrigade.scb.module;

import cn.ksmcbrigade.scb.command.Command;
import cn.ksmcbrigade.scb.config.HUD02Config;
import cn.ksmcbrigade.scb.guis.anotherFeatureList.FeatureList2;
import cn.ksmcbrigade.scb.module.events.block.BlockShapeEvent;
import cn.ksmcbrigade.scb.module.events.misc.CheckHasEffectEvent;
import cn.ksmcbrigade.scb.module.events.misc.GetOptionValueEvent;
import cn.ksmcbrigade.scb.module.events.misc.TimerEvent;
import cn.ksmcbrigade.scb.module.events.network.PacketEvent;
import cn.ksmcbrigade.scb.module.events.render.*;
import cn.ksmcbrigade.scb.uitls.CommandUtils;
import cn.ksmcbrigade.scb.uitls.JNAUtils;
import cn.ksmcbrigade.scb.SimpleClientBase;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.options.AccessibilityOptionsScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.io.File;

@EventBusSubscriber(value = Dist.CLIENT,modid = SimpleClientBase.MODID)
public class HacksEventHandler {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void render(RenderGuiLayerEvent.Pre event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.render();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void render(RenderGuiEvent.Pre event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
            //System.out.println("rendering game!!!!!!!!!!!!!!!!!!!!!!");
        }

        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.renderGameMix(event.getGuiGraphics());
                //System.out.println("module !!!!!!!!!!!!!!!!!!!!!!!!!!! :rendering game!!!!!!!!!!!!!!!!!!!!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void render(RenderGameMix event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
            //System.out.println("rendering game!!!!!!!!!!!!!!!!!!!!!!");
        }

        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.renderGame(event.drawContext);
                //System.out.println("module !!!!!!!!!!!!!!!!!!!!!!!!!!! :rendering game!!!!!!!!!!!!!!!!!!!!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent()
    public static void key(InputEvent.Key event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        if(Boolean.parseBoolean(System.getProperty("java.awt.headless"))){
            System.setProperty("java.awt.headless","false");
        }

        Minecraft MC = Minecraft.getInstance();

        if(SimpleClientBase.screenKey.isDown()){
            MC.setScreen(new AccessibilityOptionsScreen(MC.screen,MC.options));
        }

        if(HUD02Config.START.isDown()){
            MC.setScreen(new FeatureList2());
        }

        SimpleClientBase.modules.stream().toList().forEach(module -> {
            try {
                if(module.enabled){
                    module.keyInput(event.getKey(),false);
                }

                if(module.key!=-1 && JNAUtils.isPressed(module.key)){
                    module.setEnabled(!module.enabled);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent()
    public static void client(ClientTickEvent.Pre event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.clientTick(MC);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent()
    public static void clientPost(ClientTickEvent.Post event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.clientPostTick(MC);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent()
    public static void level(LevelTickEvent.Pre event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.worldTick(MC,event.getLevel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent()
    public static void levelPost(LevelTickEvent.Post event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.worldPostTick(MC,event.getLevel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent()
    public static void player(PlayerTickEvent.Pre event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.playerTick(MC,event.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent()
    public static void playerPost(PlayerTickEvent.Post event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.playerPostTick(MC,event.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void screen(ScreenshotEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        try {
            JsonObject data = new JsonObject();
            data.addProperty("result",event.getResultMessage().getString());
            data.addProperty("file",event.getScreenshotFile().getPath());
            SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
                try {
                    JsonObject newData = module.screenshot(data);
                    if(newData!=data){
                        event.setResultMessage(Component.nullToEmpty(newData.get("result").getAsString()));
                        event.setScreenshotFile(new File(newData.get("file").getAsString()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @SubscribeEvent
    public static void event01(GetOptionValueEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.getOptionInstanceEvent(MC,event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void event02(FluidTypeInCameraEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.fluidInCameraEvent(MC,event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void event03(BlockShapeEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.blockShape(MC,event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void event04(RenderBlockEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.renderBlockEvent(MC,event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void event05(PacketEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.packetEvent(MC,event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void event06(RenderOverlayEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.renderTexOverlayEvent(MC,event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void event07(ZoomEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.zoomEvent(MC,event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void event8(DayTimeEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.dayTime(MC,event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void event9(TimerEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.timerChange(MC,event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void event10(CheckHasEffectEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.hasEffect(MC,event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent
    public static void event11(RenderFireEvent event) throws Exception {
        while (!SimpleClientBase.init){
            SimpleClientBase.init();
        }

        Minecraft MC = Minecraft.getInstance();
        SimpleClientBase.modules.stream().filter(module -> module.enabled).toList().forEach(module -> {
            try {
                module.renderFire(MC,event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SubscribeEvent()
    public static void command(ClientChatEvent event) throws Exception {
        String message = event.getMessage();
        if(message.startsWith(".")){
            Minecraft MC = Minecraft.getInstance();
            Player player = MC.player;
            String name = CommandUtils.getName(message);
            Command command = CommandUtils.get(name);
            if(command==null && player!=null){
                player.sendSystemMessage(Component.nullToEmpty("Can't found the command: "+name));
            }
            else if(player!=null){
                String[] args = CommandUtils.getArgs(message);
                if(args.length>=command.length){
                    command.onCommand(MC,player,args);
                }
                else{
                    player.sendSystemMessage(Component.nullToEmpty("The command requires at least {} args: ".replace("{}",String.valueOf(command.length))+name));
                }
            }
            event.setCanceled(true);
        }
    }
}
