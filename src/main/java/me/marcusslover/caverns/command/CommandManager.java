package me.marcusslover.caverns.command;

import me.marcusslover.caverns.Caverns;
import me.marcusslover.caverns.utils.ColorUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class CommandManager {
    private static CommandManager instance;

    private CommandManager() {
        instance = this;
    }

    public void addCommand(ICommand command) {
        Class<? extends ICommand> aClass = command.getClass();
        Command[] annotationsByType = aClass.getAnnotationsByType(Command.class);
        if (annotationsByType.length == 0) {
            return;
        }
        Command commandData = annotationsByType[0];
        String label = commandData.label();
        String[] aliases = commandData.aliases();
        String permission = commandData.permission();
        String description = commandData.description();

        org.bukkit.command.Command cmd = new org.bukkit.command.Command(label) {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, String[] args) {
                if (!sender.hasPermission(permission)) {
                    sender.sendMessage(ColorUtil.toColor("&cYou don't have permission to execute this command!"));
                    return true;
                }
                command.onCommand(new CommandContext(sender, args));
                return true;
            }
        };
        cmd.setDescription(description);
        cmd.setAliases(Arrays.asList(aliases));

        CraftServer server = (CraftServer) Caverns.getInstance().getServer();
        SimpleCommandMap commandMap = server.getCommandMap();
        commandMap.register("caverns", cmd);
    }

    public static CommandManager getInstance() {
        return instance == null ? new CommandManager() : instance;
    }
}
