package me.marcusslover.caverns.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ChatUtil {
    public static void msg(Player player, String message) {
        player.sendMessage(ColorUtil.toColor(message));
    }

    public static void error(Player player, String message) {
        msg(player, "%hex(#ff2424)❌ %hex(#ff6666)" + message);
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.25f);
    }

    public static void success(Player player, String message) {
        msg(player, "%hex(#00d115)✔ %hex(#59ff6a)" + message);
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.25f);
    }

    public static void usage(Player player, String labelAndUsage) {
        msg(player, "%hex(#4aa8a6)⚐ %hex(#ADE1E5)Correct usage: &f/"+labelAndUsage+"%hex(#ADE1E5)!");
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 1.0f, 1.25f);
    }
}
