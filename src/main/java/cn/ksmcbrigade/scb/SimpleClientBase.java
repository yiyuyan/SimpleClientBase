package cn.ksmcbrigade.scb;

import cn.ksmcbrigade.scb.BuiltInModules.combat.AutoRespawn;
import cn.ksmcbrigade.scb.BuiltInModules.misc.MEMZ;
import cn.ksmcbrigade.scb.BuiltInModules.movement.AirJump;
import cn.ksmcbrigade.scb.BuiltInModules.movement.NoFall;
import cn.ksmcbrigade.scb.BuiltInModules.movement.Timer;
import cn.ksmcbrigade.scb.BuiltInModules.overlay.NoBlindOverlay;
import cn.ksmcbrigade.scb.BuiltInModules.overlay.NoFireOverlay;
import cn.ksmcbrigade.scb.BuiltInModules.overlay.NoPumpkinOverlay;
import cn.ksmcbrigade.scb.BuiltInModules.overlay.NoUnderOverlay;
import cn.ksmcbrigade.scb.BuiltInModules.render.*;
import cn.ksmcbrigade.scb.BuiltInModules.render.esp.*;
import cn.ksmcbrigade.scb.alt.AltManager;
import cn.ksmcbrigade.scb.commands.Help;
import cn.ksmcbrigade.scb.commands.Pos;
import cn.ksmcbrigade.scb.config.HUD02Config;
import cn.ksmcbrigade.scb.config.HUDConfig;
import cn.ksmcbrigade.scb.guis.group.Group;
import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import cn.ksmcbrigade.scb.uitls.ClientRegistry;
import cn.ksmcbrigade.scb.uitls.CommandUtils;
import cn.ksmcbrigade.scb.uitls.ModuleUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Mod(SimpleClientBase.MODID)
public class SimpleClientBase {

    public static boolean init = false;

    public static File configFile = new File("config/scb/enabledList.json");
    public static File KeyboardConfigFile = new File("config/scb/keys.json");

    public static ArrayList<Module> modules = new ArrayList<>();

    public static AltManager altManager;

    public static final String MODID = "scb";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static int ScreenKey = KeyEvent.VK_V;

    public static final KeyMapping screenKey = new KeyMapping("options.accessibility.title",GLFW.GLFW_KEY_V,"key.categories.gameplay");

    public SimpleClientBase(IEventBus modEventBus, ModContainer modContainer) throws Exception {
        if(Boolean.parseBoolean(System.getProperty("java.awt.headless"))){
            System.setProperty("java.awt.headless","false");
        }
        if(!init){
            modContainer.registerConfig(ModConfig.Type.CLIENT,HUDConfig.CONFIG_SPEC,"scb/scb-hud-config.toml");
            modContainer.registerConfig(ModConfig.Type.CLIENT,HUD02Config.CONFIG_SPEC,"scb/scb-hud02-config.toml");
            init();
            LOGGER.info("Reloaded the HUD config.");
        }
        new Thread(()->{
            while (!HUDConfig.CONFIG_SPEC.isLoaded()){
                Thread.yield();
            }
            while (Minecraft.getInstance().font==null){
                Thread.yield();
            }
            HUDConfig.init = false;
            HUDConfig.init();
        }).start();
    }

    public static void init() throws Exception {

        if(Boolean.parseBoolean(System.getProperty("java.awt.headless"))){
            System.setProperty("java.awt.headless","false");
        }

        ClientRegistry.registerKeyBinding(screenKey);

        ClientRegistry.registerKeyBinding(HUDConfig.UP);
        ClientRegistry.registerKeyBinding(HUDConfig.DOWN);
        ClientRegistry.registerKeyBinding(HUDConfig.LEFT);
        ClientRegistry.registerKeyBinding(HUDConfig.RIGHT);

        ClientRegistry.registerKeyBinding(HUDConfig.SET);

        ClientRegistry.registerKeyBinding(HUD02Config.START);

        LOGGER.info("Register the keys.");

        new File("config").mkdirs();
        new File("config/scb").mkdirs();
        new File("config/scb/modules").mkdirs();

        HUDConfig.init();
        ModuleType.check();

        altManager = new AltManager();

        ModuleUtils.add(new HUD());
        ModuleUtils.add(new ModulesList());
        ModuleUtils.add(new RainbowGui());
        ModuleUtils.add(new MEMZ());

        ModuleUtils.add(new AutoRespawn());
        ModuleUtils.add(new NoFall());

        ModuleUtils.add(new NoUnderOverlay());
        ModuleUtils.add(new FullBright());

        ModuleUtils.add(new AirJump());

        ModuleUtils.add(new OnlyDay());
        ModuleUtils.add(new NoPumpkinOverlay());
        ModuleUtils.add(new NoBlindOverlay());
        ModuleUtils.add(new NoFireOverlay());
        ModuleUtils.add(new Zoom());

        ModuleUtils.add(new OnlyMe());
        ModuleUtils.add(new Chams());

        ModuleUtils.add(new ChestESP());
        ModuleUtils.add(new PlayerESP());
        ModuleUtils.add(new MobESP());
        ModuleUtils.add(new AnimalESP());
        ModuleUtils.add(new MonsterESP());
        ModuleUtils.add(new EntityESP());
        ModuleUtils.add(new ProjectileESP());

        ModuleUtils.add(new Timer());

        ModuleUtils.add(new Module("TestModule", ModuleType.BLOCK));

        CommandUtils.add(new Pos());
        CommandUtils.add(new Help());

        if(!configFile.exists()){
            Files.writeString(configFile.toPath(),"[]");
        }

        JsonArray array = JsonParser.parseString(Files.readString(configFile.toPath())).getAsJsonArray();
        array.forEach(k -> {
            Module module = ModuleUtils.get(k.getAsString());
            if(module!=null){
                try {
                    module.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ModuleUtils.save();

        if(!KeyboardConfigFile.exists()){
            JsonObject keysMap = new JsonObject();
            keysMap.addProperty("NModuleConfiguration",ScreenKey);
            modules.forEach(e -> keysMap.addProperty(e.name,e.key));
            Files.writeString(KeyboardConfigFile.toPath(),keysMap.toString());
        }

        JsonObject keysMap = JsonParser.parseString(Files.readString(KeyboardConfigFile.toPath())).getAsJsonObject();

        ScreenKey = keysMap.get("NModuleConfiguration").getAsInt();
        screenKey.setKeyModifierAndCode(screenKey.getDefaultKeyModifier(),InputConstants.Type.KEYSYM.getOrCreate(ScreenKey));

        keysMap.keySet().forEach(k -> {
            Module module = ModuleUtils.get(k);
            if(module!=null){
                module.setKey(keysMap.get(k).getAsInt());
            }
        });

        ModuleUtils.saveKeys();

        init = true;

        LOGGER.info("SimpleClientBase mod loaded!");
    }

    public static boolean checkGroupEmpty(Group group){
        boolean is = modules.stream().filter(module -> module.type.group.renderer.title.equalsIgnoreCase(group.renderer.title)).toList().isEmpty();
        if(!is){
            AtomicBoolean no = new AtomicBoolean(false);
            modules.stream().filter(module -> module.type.group.renderer.title.equalsIgnoreCase(group.renderer.title)).forEach(module -> {
                if(module.type.group!=group){
                    if(!group.features.contains(module)){
                        group.add(module);
                    }
                    no.set(true);
                }
            });
            if(no.get()){
                is = true;
            }
        }
        return is;
    }
}
