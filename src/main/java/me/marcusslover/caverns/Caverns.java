package me.marcusslover.caverns;

import me.marcusslover.caverns.data.DataListener;
import me.marcusslover.caverns.data.DataManager;
import me.marcusslover.caverns.event.EventManager;
import me.marcusslover.caverns.sidebar.SidebarListener;
import me.marcusslover.caverns.utils.IPluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class Caverns extends JavaPlugin implements IPluginLoader {
    private static Caverns instance;

    @Override
    public void onEnable() {
        instance = this;
        this.createPluginFolder();
        this.createDirectory(getDataFolder(), "players");

        this.addListener(new DataListener());
        this.addListener(new SidebarListener());

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
