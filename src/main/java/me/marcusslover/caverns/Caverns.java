package me.marcusslover.caverns;

import me.marcusslover.caverns.api.data.DataListener;
import me.marcusslover.caverns.api.data.DataManager;
import me.marcusslover.caverns.api.event.EventManager;
import me.marcusslover.caverns.api.sidebar.SidebarListener;
import me.marcusslover.caverns.api.utils.IPluginLoader;
import me.marcusslover.caverns.game.command.RankCommand;
import me.marcusslover.caverns.game.player.PlayerListener;
import me.marcusslover.caverns.game.player.PlayerMenu;
import org.bukkit.plugin.java.JavaPlugin;

public final class Caverns extends JavaPlugin implements IPluginLoader {
    private static Caverns instance;

    @Override
    public void onEnable() {
        instance = this;
        this.createPluginFolder();
        this.createDirectory(getDataFolder(), "players");

        // Event listeners
        this.addListener(new DataListener());
        this.addListener(new SidebarListener());
        this.addListener(new PlayerListener());

        // Menus
        this.addMenu(new PlayerMenu());

        // Commands
        this.addCommand(new RankCommand());

        // Other
        EventManager.startTimer();
    }

    @Override
    public void onDisable() {
        DataManager.getInstance().savePlayers();
    }

    public static Caverns getInstance() {
        return instance;
    }
}
