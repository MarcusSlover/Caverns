package me.marcusslover.caverns.player;

import me.marcusslover.caverns.data.DataLoadEvent;
import me.marcusslover.caverns.data.PlayerContainer;
import me.marcusslover.caverns.data.Rank;
import me.marcusslover.caverns.data.RankManager;
import me.marcusslover.caverns.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(""); // Get rid of the join message
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ColorUtil.toColor("&c← &*» &f" + player.getName() + " left."));
    }

    @EventHandler
    public void onLoad(DataLoadEvent event) {
        Player player = event.getPlayer();
        PlayerContainer container = event.getPlayerContainer();

        boolean isFirstJoin = false;
        String message;

        if (!container.has("firstJoin")) {
            isFirstJoin = true;
            container.setLong("firstJoin", System.currentTimeMillis());
            container.setString("rank", "player");
            message = "%hex(#4DFF4D)★ &7" + player.getName() + " %hex(#99FF99)has joined for the first time!";
        } else {
            message = "&a→ &8» &f" + player.getName() + " joined.";
        }
        message = ColorUtil.toColor(message);

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(message);
            if (isFirstJoin) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        PlayerContainer container = PlayerContainer.get(player);
        if (container != null) {
            int prestige = container.getInteger("prestige", 0);
            String rank = container.getString("rank", "player");
            Rank rank1 = RankManager.get(rank);
            String prefix = rank1.prefix;
            String chatColor = rank1.chatColor;

            String chat = " " + chatColor + "⏵ &f" + player.getName() + ": " + chatColor + event.getMessage();
            if (prestige > 0) {
                chat = prefix + " &f&l" + prestige + chat;
            } else {
                chat = prefix + chat;
            }
            chat = ColorUtil.toColor(chat);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.sendMessage(chat);
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(!event.getPlayer().isOp());
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(!event.getEntity().isOp());
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }
}
