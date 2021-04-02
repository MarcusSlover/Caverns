package me.marcusslover.caverns.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;

public class FileUtil {
    public static final JsonParser JSON_PARSER = new JsonParser();

    public static JsonElement readJsonElement(File file) {
        return readJsonElement(file, null);
    }
    public static JsonElement readJsonElement(File file, JsonElement defaultValue) {
        JsonElement element;
        try {
            FileReader fileReader = new FileReader(file);
            element = JSON_PARSER.parse(fileReader);
        } catch (Exception ex) {
            return defaultValue;
        }
        return element;
    }

    public static void writeJsonElement(JsonElement jsonElement, File file) throws IOException {
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new IOException("Couldn't create parent directory!");
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(new Gson().toJson(jsonElement));
        writer.close();
    }
}
