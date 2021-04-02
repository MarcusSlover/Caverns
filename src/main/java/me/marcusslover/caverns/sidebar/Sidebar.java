package me.marcusslover.caverns.sidebar;

import me.marcusslover.caverns.utils.IColorable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Sidebar implements IColorable {
    private static final ChatColor[] colors = ChatColor.values();
    private static final Map<UUID, Sidebar> PLAYER_SIDEBAR_MAP = new HashMap<>();
    private static final ScoreboardManager SCOREBOARD_MANAGER = Bukkit.getScoreboardManager();
    private static long uniqueNumber = 0L;

    private final Scoreboard scoreboard;
    private final Objective objective;

    public Sidebar(String title) {
        this.scoreboard = SCOREBOARD_MANAGER.getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("caverns_"+uniqueNumber, "dummy", this.toColor(title));
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        uniqueNumber += 1;
    }

    public Sidebar addLine(String prefix, String suffix) {
        int size = getSize();
        Team team = scoreboard.registerNewTeam("caverns_" + size);
        String entry = colors[size].toString() + colors[size / 4].toString() + colors[colors.length - 1];

        team.setPrefix(this.toColor(prefix));
        team.setSuffix(this.toColor(suffix));
        team.addEntry(entry);

        objective.getScore(entry).setScore(16 - size);

        return this;
    }

    public Sidebar setLine(int index, String prefix, String suffix) {
        Team team = scoreboard.getTeam("caverns_" + index);
        if (team == null) {
            return this;
        }

        team.setPrefix(this.toColor(prefix));
        team.setSuffix(this.toColor(suffix));

        return this;
    }

    public void clearFields() {
        for (Team team : scoreboard.getTeams()) {
            team.unregister();
        }
    }

    public int getSize() {
        return scoreboard.getTeams().size();
    }

    public void show(Player player) {
        PLAYER_SIDEBAR_MAP.put(player.getUniqueId(), this);
        player.setScoreboard(scoreboard);
    }

    public static Sidebar getByPlayer(Player player) {
        return PLAYER_SIDEBAR_MAP.get(player.getUniqueId());
    }
}
