package cn.ksmcbrigade.scb.uitls;

import com.google.gson.JsonElement;

public class JsonTypeChecker {
    public static boolean isInt(JsonElement element) {
        return element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber() && element.getAsJsonPrimitive().getAsNumber().intValue() == element.getAsJsonPrimitive().getAsInt();
    }

    public static boolean isFloat(JsonElement element) {
        return element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber() && element.getAsJsonPrimitive().getAsNumber().floatValue() == element.getAsJsonPrimitive().getAsFloat();
    }

    public static boolean isDouble(JsonElement element) {
        return element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber() && element.getAsJsonPrimitive().getAsNumber().doubleValue() == element.getAsJsonPrimitive().getAsDouble();
    }
}
