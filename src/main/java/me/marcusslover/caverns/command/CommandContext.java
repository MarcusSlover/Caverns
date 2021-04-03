package me.marcusslover.caverns.command;

import me.marcusslover.caverns.data.PlayerContainer;
import me.marcusslover.caverns.data.Rank;
import me.marcusslover.caverns.data.RankManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class CommandContext {
    final CommandSender sender;
    final String[] args;

    public CommandContext(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
    }

    public void asPlayer(Consumer<Player> consumer) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            consumer.accept(player);
        }
    }

    public boolean hasRank(String rank) {
        Rank rank1 = RankManager.get(rank);
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerContainer container = PlayerContainer.get(player);
            if (container != null) {
                String playerRank = container.getString("rank", "player");
                Rank rank2 = RankManager.get(playerRank);
                return rank2.priority >= rank1.priority;
            }
        }

        return false;
    }
}
