package me.marcusslover.caverns.command;

import me.marcusslover.caverns.data.PlayerContainer;
import me.marcusslover.caverns.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Command(label = "rank")
public class RankCommand implements ICommand {

    @Override
    public void onCommand(CommandContext context) {
        context.asPlayer(player -> {
            if (context.hasRank("credits") || player.isOp()) {
                String[] args = context.args;
                if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        ChatUtil.error(player, "Could not find the target!!");
                        return;
                    }
                    PlayerContainer container = PlayerContainer.get(target);
                    if (container == null) {
                        ChatUtil.error(player, "Could not find the target's data!");
                        return;
                    }
                    container.setString("rank", args[1]);
                    ChatUtil.success(player, "The rank was set and updated!");
                } else {
                    ChatUtil.usage(player, "rank <player> <rank>");
                }
            } else {
                ChatUtil.error(player, "No permission!");
            }
        });
    }
}
