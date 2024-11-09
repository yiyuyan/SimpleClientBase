package cn.ksmcbrigade.scb.alt;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;

import java.util.Optional;
import java.util.UUID;

public record Alt(String name, UUID uuid, String token) {
    public static Alt getSelf(Minecraft MC){
        return new Alt(MC.getUser().getName(),MC.getUser().getProfileId(),MC.getUser().getAccessToken());
    }

    public static Alt get(String name){
        return new Alt(name,UUID.randomUUID(),"0");
    }

    public static void change(Minecraft MC,Alt alt){
        User.Type type = alt.token.equals("0")? User.Type.LEGACY: User.Type.MSA;
        MC.user = new User(alt.name,alt.uuid,alt.token, Optional.empty(),Optional.empty(),type);
    }

    public JsonObject toJson(){
        JsonObject object = new JsonObject();
        object.addProperty("name",this.name);
        object.addProperty("uuid",this.uuid.toString());
        object.addProperty("accessToken",this.token);
        return object;
    }

    public static Alt parse(JsonObject object){
        return new Alt(object.get("name").getAsString(),UUID.fromString(object.get("uuid").getAsString()),object.get("accessToken").getAsString());
    }
}
