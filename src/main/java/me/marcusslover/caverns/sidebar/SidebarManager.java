package me.marcusslover.caverns.sidebar;

import me.marcusslover.caverns.data.PlayerContainer;
import me.marcusslover.caverns.utils.NumberUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SidebarManager {
    private static SidebarManager instance;

    private SidebarManager() {
        instance = this;
    }

    public void createSidebar(Player player, PlayerContainer container) {
        double coins = container.getDouble("coins", 0.0);

        Sidebar sidebar = new Sidebar("&f&lTHE CAVERNS");
        sidebar.addEmpty();
        sidebar.addLine("&e☀ &fDay", " &7(6:00)");
        sidebar.addEmpty();
        sidebar.addLine("&7◆ &fPlayers:", "&e"+Bukkit.getOnlinePlayers().size());
        sidebar.addLine("&7◆ &fCoins:", "&e"+ NumberUtil.toFancyNumber(coins) + "&6⛁");
        sidebar.addEmpty();
        sidebar.addLine("&5⇒ &fEvent: ", "%hex(#FF99FF)None");
        sidebar.addEmpty();
    }

    public static SidebarManager getInstance() {
        return instance == null ? new SidebarManager() : instance;
    }
}
