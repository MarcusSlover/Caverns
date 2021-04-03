package me.marcusslover.caverns.api.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.marcusslover.caverns.Caverns;
import me.marcusslover.caverns.api.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {
    private static DataManager instance;
    private final Map<UUID, PlayerContainer> players;
    private static final Caverns CAVERNS = Caverns.getInstance();

    private DataManager() {
        instance = this;
        players = new HashMap<>();
    }

    void loadPlayer(UUID uuid) {
        if (players.containsKey(uuid)) {
            return;
        }

        String fileName = uuid.toString() + ".json";
        File playerFile = new File(CAVERNS.getDataFolder(), fileName);
        JsonElement jsonElement = FileUtil.readJsonElement(playerFile, new JsonObject());
        PlayerContainer playerContainer = new PlayerContainer(jsonElement.getAsJsonObject());
        players.put(uuid, playerContainer);
    }

    void savePlayer(UUID uuid) {
        if (!players.containsKey(uuid)) {
            return;
        }

        String fileName = uuid.toString() + ".json";
        File playerFile = new File(CAVERNS.getDataFolder(), fileName);
        PlayerContainer playerContainer = players.get(uuid);

        try {
            FileUtil.writeJsonElement(playerContainer.toJson(), playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePlayers() {
        players.keySet().forEach(this::savePlayer);
    }

    public PlayerContainer get(UUID uuid) {
        return players.get(uuid);
    }

    public Map<UUID, PlayerContainer> getPlayers() {
        return players;
    }

    public static DataManager getInstance() {
        return instance == null ? new DataManager() : instance;
    }

}
