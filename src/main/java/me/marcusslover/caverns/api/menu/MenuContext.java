package me.marcusslover.caverns.api.menu;

import me.marcusslover.caverns.api.item.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuContext {
    public final InventoryClickEvent event;
    public final Player player;
    public final Item item;
    public final ClickType click;
    private boolean cancelled;

    public MenuContext(InventoryClickEvent event) {
        this.event = event;
        this.player = (Player) event.getWhoClicked();
        this.item = new Item(event.getCurrentItem());
        this.click = event.getClick();
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
