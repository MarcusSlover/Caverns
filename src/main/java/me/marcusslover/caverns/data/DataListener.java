package me.marcusslover.caverns.data;

import me.marcusslover.caverns.Caverns;
import me.marcusslover.caverns.utils.TaskUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class DataListener implements Listener {
    private static final DataManager DATA_MANAGER = DataManager.getInstance();
    private static final Caverns CAVERNS = Caverns.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        TaskUtil.runAsync(() -> DATA_MANAGER.loadPlayer(uuid), (whenDone) -> {
            CAVERNS.getLogger().info("Loaded " + player.getName() + "'s data in " + whenDone + "ms");
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        TaskUtil.runAsync(() -> DATA_MANAGER.savePlayer(uuid), (whenDone) -> {
            CAVERNS.getLogger().info("Saved " + player.getName() + "'s data in " + whenDone + "ms");
        });
    }
}
