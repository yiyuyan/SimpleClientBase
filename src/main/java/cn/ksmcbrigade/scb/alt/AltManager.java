package cn.ksmcbrigade.scb.alt;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class AltManager {
    public ArrayList<Alt> alts = new ArrayList<>();

    private final File config = new File("config/scb/scb-alts.json");

    public AltManager() throws IOException {
        new File("config").mkdirs();
        new File("config/scb").mkdirs();

        if(config.exists()){
            JsonArray array = JsonParser.parseString(FileUtils.readFileToString(config)).getAsJsonArray();
            for (JsonElement jsonElement : array) {
                if(jsonElement instanceof JsonObject object){
                    this.alts.add(Alt.parse(object));
                }
            }
        }

        save(false);
    }

    public void add(Alt alt) throws IOException {
        this.alts.add(alt);
        this.save(true);
    }

    public void replace(UUID altUUID, Alt alt) throws IOException {
        for (int i = 0; i < alts.size(); i++) {
            Alt alt1 = alts.get(i);
            if(alt1.uuid().equals(altUUID)){
                alts.set(i,alt);
            }
        }
        save(true);
    }

    public void save(boolean t) throws IOException{
        if(alts.isEmpty()){
            alts.add(Alt.getSelf(Minecraft.getInstance()));
        }
        if(!config.exists() || t){
            JsonArray array = new JsonArray();
            for (Alt alt : alts) {
                array.add(alt.toJson());
            }
            FileUtils.write(config, array.toString());
        }
    }

    public static enum Action{
        ADD,
        EDIT
    }
}
