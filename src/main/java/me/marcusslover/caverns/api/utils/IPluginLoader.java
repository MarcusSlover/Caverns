package me.marcusslover.caverns.api.utils;

import me.marcusslover.caverns.Caverns;
import me.marcusslover.caverns.api.command.CommandManager;
import me.marcusslover.caverns.api.command.ICommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.io.File;

public interface IPluginLoader {
    default void addListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, Caverns.getInstance());
    }

    default void addCommand(ICommand iCommand) {
        CommandManager.getInstance().addCommand(iCommand);
    }

    default void createDirectory(File parent, String name) {
        File file = new File(parent, name);
        if (!file.exists()) //noinspection ResultOfMethodCallIgnored
            file.mkdirs();
    }

    default void createPluginFolder() {
        File dataFolder = Caverns.getInstance().getDataFolder();
        if (!dataFolder.exists()) //noinspection ResultOfMethodCallIgnored
            dataFolder.mkdirs();
    }

}
