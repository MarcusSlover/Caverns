package me.marcusslover.caverns.api.command;

import me.marcusslover.caverns.api.data.PlayerContainer;
import me.marcusslover.caverns.api.data.Rank;
import me.marcusslover.caverns.api.data.RankManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class CommandContext {
    public final CommandSender sender;
    public final String[] args;

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
