package me.marcusslover.caverns.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JsonWrapper {
    protected JsonObject jsonObject;

    public JsonWrapper(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setInteger(String path, int value) {
        jsonObject.addProperty(path, value);
    }

    public int getInteger(String path, int defaultValue) {
        if (jsonObject.has(path)) {
            return jsonObject.get(path).getAsInt();
        } else {
            this.setInteger(path, defaultValue);
        }

        return defaultValue;
    }

    public void setLong(String path, long value) {
        jsonObject.addProperty(path, value);
    }

    public long getLong(String path, long defaultValue) {
        if (jsonObject.has(path)) {
            return jsonObject.get(path).getAsLong();
        } else {
            this.setLong(path, defaultValue);
        }
        return defaultValue;
    }

    public void setDouble(String path, double value) {
        jsonObject.addProperty(path, value);
    }

    public double getDouble(String path, double defaultValue) {
        if (jsonObject.has(path)) {
            return jsonObject.get(path).getAsDouble();
        } else {
            this.setDouble(path, defaultValue);
        }
        return defaultValue;
    }

    public void setFloat(String path, float value) {
        jsonObject.addProperty(path, value);
    }

    public float getFloat(String path, float defaultValue) {
        if (jsonObject.has(path)) {
            return jsonObject.get(path).getAsFloat();
        } else {
            this.setFloat(path, defaultValue);
        }
        return defaultValue;
    }

    public void setUUID(String path, UUID value) {
        jsonObject.addProperty(path, value.toString());
    }

    public UUID getUUID(String path, UUID defaultValue) {
        if (jsonObject.has(path)) {
            return UUID.fromString(jsonObject.get(path).getAsString());
        } else {
            this.setUUID(path, defaultValue);
        }
        return defaultValue;
    }

    public void setString(String path, String value) {
        jsonObject.addProperty(path, value);
    }

    public String getString(String path, String defaultValue) {
        if (jsonObject.has(path)) {
            return jsonObject.get(path).getAsString();
        } else {
            this.setString(path, defaultValue);
        }
        return defaultValue;
    }

    public void setBoolean(String path, boolean value) {
        jsonObject.addProperty(path, value);
    }

    public boolean getBoolean(String path, boolean defaultValue) {
        if (jsonObject.has(path)) {
            return jsonObject.get(path).getAsBoolean();
        } else {
            this.setBoolean(path, defaultValue);
        }
        return defaultValue;
    }

    public void setJsonObject(String path, JsonObject value) {
        jsonObject.add(path, value);
    }

    public JsonObject getJsonObject(String path, JsonObject defaultValue) {
        if (jsonObject.has(path)) {
            return jsonObject.get(path).getAsJsonObject();
        } else {
            this.setJsonObject(path, defaultValue);
        }
        return defaultValue;
    }

    public void setJsonArray(String path, JsonArray value) {
        jsonObject.add(path, value);
    }

    public JsonArray getJsonArray(String path, JsonArray defaultValue) {
        if (jsonObject.has(path)) {
            return jsonObject.get(path).getAsJsonArray();
        } else {
            this.setJsonArray(path, defaultValue);
        }
        return defaultValue;
    }

    public void setStringList(String path, List<String> value) {
        JsonArray jsonArray = new JsonArray();
        for (String s : value) {
            jsonArray.add(s);
        }
        this.setJsonArray(path, jsonArray);
    }

    public List<String> getStringList(String path, List<String> defaultValue) {
        if (jsonObject.has(path)) {
            List<String> strings = new ArrayList<>();
            JsonArray asJsonArray = jsonObject.get(path).getAsJsonArray();
            for (JsonElement jsonElement : asJsonArray) {
                strings.add(jsonElement.getAsString());
            }
            return strings;
        } else {
            this.setStringList(path, defaultValue);
        }
        return defaultValue;
    }

    public void setEnum(String path, Enum<?> value) {
        this.setString(path, value.toString());
    }

    public <T extends Enum<T>> T getEnum(String path, Class<T> enumType, Enum<?> defaultValue) {
        String string = this.getString(path, defaultValue.toString());
        return Enum.valueOf(enumType, string);
    }

    public void setMaterial(String path, Material material) {
        this.setString(path, material.toString());
    }

    public Material getMaterial(String path, Material defaultValue) {
        return Material.matchMaterial(getString(path, defaultValue.toString()));
    }

    public JsonObject toJson() {
        return this.getJsonObject();
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public boolean has(String key) {
        return jsonObject.has(key);
    }
}
