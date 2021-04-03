package me.marcusslover.caverns.game.player;

import me.marcusslover.caverns.api.data.DataLoadEvent;
import me.marcusslover.caverns.api.data.PlayerContainer;
import me.marcusslover.caverns.api.data.Rank;
import me.marcusslover.caverns.api.data.RankManager;
import me.marcusslover.caverns.api.item.Item;
import me.marcusslover.caverns.api.menu.IMenu;
import me.marcusslover.caverns.api.sidebar.SidebarManager;
import me.marcusslover.caverns.api.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    public static final Item MENU = new Item(Material.FLINT, 1, "&8◇ &7Menu &6◇")
            .withTag("menu", true);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(""); // Get rid of the join message
        Player player = event.getPlayer();
        player.getInventory().setItem(8, MENU.clone().bukkitItem());
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ColorUtil.toColor("&c← &*» &f" + player.getName() + " left."));
        SidebarManager.getInstance().removeTablist(player);
    }

    @EventHandler (priority = EventPriority.HIGH)
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
        SidebarManager.getInstance().updateTablist(player);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(!event.getPlayer().isOp());
        Action action = event.getAction();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            Item item = new Item(event.getItem());
            if (item.isValid()) {
                if (item.hasTag("menu")) {
                    IMenu.open(player, PlayerMenu.class);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1.0f, 1.0f);
                    event.setCancelled(true);
                }
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
    public void onSwapHands(PlayerSwapHandItemsEvent event) {
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
