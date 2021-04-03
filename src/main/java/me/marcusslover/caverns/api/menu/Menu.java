package me.marcusslover.caverns.api.menu;

import me.marcusslover.caverns.api.item.Item;
import me.marcusslover.caverns.api.utils.IColorable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Menu implements IColorable {
    public final String name;
    public final Inventory inventory;
    public final int size;

    public Menu(String name) {
        this(name, 3 * 9);
    }

    public Menu(String name, int size) {
        this.name = name;
        this.size = size;
        this.inventory = Bukkit.createInventory(null, size, this.toColor(name));
    }

    public Menu setItem(int slot, Item item) {
        inventory.setItem(slot, item.bukkitItem());
        return this;
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }
}
