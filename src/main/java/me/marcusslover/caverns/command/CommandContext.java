package me.marcusslover.caverns.command;

import org.bukkit.command.CommandSender;

public class CommandContext {
    final CommandSender sender;
    final String[] args;

    public CommandContext(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
    }
}
