package me.marcusslover.caverns.api.sidebar;

import me.marcusslover.caverns.api.data.DataLoadEvent;
import me.marcusslover.caverns.api.data.PlayerContainer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SidebarListener implements Listener {
    @EventHandler
    public void onJoin(DataLoadEvent event) {
        Player player = event.getPlayer();
        PlayerContainer container = event.getPlayerContainer();
        SidebarManager.getInstance().createSidebar(player, container);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Sidebar sidebar = Sidebar.getByPlayer(player);
        if (sidebar != null) {
            sidebar.destroy(player);
        }
    }
}
