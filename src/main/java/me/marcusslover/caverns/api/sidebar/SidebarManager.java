package me.marcusslover.caverns.api.sidebar;

import me.marcusslover.caverns.api.data.PlayerContainer;
import me.marcusslover.caverns.api.data.Rank;
import me.marcusslover.caverns.api.data.RankManager;
import me.marcusslover.caverns.api.event.EventManager;
import me.marcusslover.caverns.api.utils.NumberUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class SidebarManager {
    private static SidebarManager instance;

    private SidebarManager() {
        instance = this;
    }

    public void removeTablist(Player player) {
        PlayerContainer container = PlayerContainer.get(player);
        if (container == null) {
            return;
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Sidebar sidebar = Sidebar.getByPlayer(onlinePlayer);
            if (sidebar == null) {
                continue;
            }
            Scoreboard scoreboard = sidebar.scoreboard;
            Team team = scoreboard.getTeam(player.getName());
            if (team != null) {
                team.unregister();
            }
        }
    }

    public void updateTablist(Player player) {
        PlayerContainer container = PlayerContainer.get(player);
        if (container == null) {
            return;
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Sidebar sidebar = Sidebar.getByPlayer(onlinePlayer);
            if (sidebar == null) {
                continue;
            }

            Scoreboard scoreboard = sidebar.scoreboard;
            Team team = scoreboard.getTeam(player.getName());
            if (team == null) {
                scoreboard.registerNewTeam(player.getName());
            }
            String rank = container.getString("rank", "player");
            Rank rank1 = RankManager.get(rank);
            team.setPrefix(rank1.chatColor + "«" + rank1.prefix + rank1.chatColor + "»");
            if (!team.hasEntry(player.getName())) {
                team.addEntry(player.getName());
            }
        }
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
