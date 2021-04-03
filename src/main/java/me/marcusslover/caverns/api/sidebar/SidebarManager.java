package me.marcusslover.caverns.api.sidebar;

import me.marcusslover.caverns.api.data.PlayerContainer;
import me.marcusslover.caverns.api.event.EventManager;
import me.marcusslover.caverns.api.utils.NumberUtil;
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
        sidebar.addLine("&7◆ &fPlayers:", " &e"+Bukkit.getOnlinePlayers().size());
        sidebar.addLine("&7◆ &fCoins:", " &e"+ NumberUtil.toFancyNumber(coins) + "&6⛁");
        sidebar.addEmpty();
        sidebar.addLine("&d⇒ &fEvent: ", EventManager.getSidebarText());
        sidebar.addEmpty();

        sidebar.show(player);
    }

    public void updatePlayers(Player player) {
        Sidebar.getByPlayer(player).setLine(3, "&7◆ &fPlayers:", "&e"+Bukkit.getOnlinePlayers().size());
    }

    public void updateCoins(Player player) {
        PlayerContainer container = PlayerContainer.get(player);
        double coins = container.getDouble("coins", 0.0d);
        Sidebar.getByPlayer(player).setLine(4, "&7◆ &fCoins:", "&e"+ NumberUtil.toFancyNumber(coins) + "&6⛁");

    }

    public void updateEvent(Player player) {
        Sidebar.getByPlayer(player).setLine(6, "&5⇒ &fEvent: ", EventManager.getSidebarText());
    }

    public static SidebarManager getInstance() {
        return instance == null ? new SidebarManager() : instance;
    }
}
