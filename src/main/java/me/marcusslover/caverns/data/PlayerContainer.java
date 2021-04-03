package me.marcusslover.caverns.data;

import com.google.gson.JsonObject;
import me.marcusslover.caverns.utils.JsonWrapper;
import org.bukkit.entity.Player;

public class PlayerContainer extends JsonWrapper {

    public PlayerContainer(JsonObject jsonObject) {
        super(jsonObject);
    }

    public static PlayerContainer get(Player player) {
        return DataManager.getInstance().get(player.getUniqueId());
    }
}
