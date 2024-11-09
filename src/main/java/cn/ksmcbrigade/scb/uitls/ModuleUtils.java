package cn.ksmcbrigade.scb.uitls;

import cn.ksmcbrigade.scb.SimpleClientBase;
import cn.ksmcbrigade.scb.module.Module;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ModuleUtils {
    public static boolean has(String name){
        for(Module module: SimpleClientBase.modules){
            if(module.name.equals(name)){
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static Module get(String name){
        for(Module module: SimpleClientBase.modules){
            if(module.name.equals(name)){
                return module;
            }
        }
        return null;
    }

    public static void set(String name,boolean enabled) throws Exception {
        for(Module module: SimpleClientBase.modules){
            if(module.name.equals(name)){
                module.setEnabled(enabled);
                break;
            }
        }
    }

    public static boolean enabled(String name){
        for(Module module: SimpleClientBase.modules){
            if(module.name.equals(name) && module.enabled){
                return true;
            }
        }
        return false;
    }

    public static void add(Module module){
        SimpleClientBase.modules.add(module);
    }

    public static void del(Module module){
        SimpleClientBase.modules.remove(module);
    }

    public static void del(int module){
        SimpleClientBase.modules.remove(module);
    }

    public static ArrayList<Module> getAll(boolean enabled){
        return enabled?new ArrayList<>(SimpleClientBase.modules.stream().filter(m -> m.enabled).toList()): SimpleClientBase.modules;
    }

    public static Module[] getNewShotAll(boolean enabled){
        ArrayList<Module> modules = new ArrayList<>();
        if(enabled){
            modules.addAll(SimpleClientBase.modules.stream().filter(m -> m.enabled).toList());
        }
        else{
            modules.addAll(SimpleClientBase.modules);
        }
        Module[] newModules = modules.toArray(new Module[0]);
        Arrays.sort(newModules, Comparator.comparing(Module::length).reversed());
        return newModules;
    }

    public static void save() throws IOException{
        JsonArray modules = new JsonArray();
        SimpleClientBase.modules.stream().filter(m -> m.enabled).toList().forEach(m -> modules.add(m.name));
        Files.writeString(SimpleClientBase.configFile.toPath(),modules.toString());
    }

    public static void saveKeys() throws IOException{
        JsonObject keysMap = new JsonObject();
        keysMap.addProperty("NModuleConfiguration", SimpleClientBase.ScreenKey);
        SimpleClientBase.modules.forEach(e -> keysMap.addProperty(e.name,e.key));
        Files.writeString(SimpleClientBase.KeyboardConfigFile.toPath(),keysMap.toString());
    }
}
