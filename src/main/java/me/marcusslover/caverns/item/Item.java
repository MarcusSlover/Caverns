package me.marcusslover.caverns.item;

import me.marcusslover.caverns.utils.IColorable;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Item implements IColorable {
    ItemStack itemStack;

    public Item(Material material) {
        this(material, 0);
    }

    public Item(Material material, int amount) {
        this(new ItemStack(material, amount));
    }

    public Item(Material material, int amount, String name, List<String> lore) {
        this(new ItemStack(material, amount));
        this.setName(name);
        this.setLore(lore);
    }

    public Item setLore(String... lore) {
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setLore(this.toColor(Arrays.asList(lore)));
        this.setItemMeta(itemMeta);
        return this;
    }

    public Item setLore(List<String> lore) {
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setLore(this.toColor(lore));
        this.setItemMeta(itemMeta);
        return this;
    }

    public Item setName(String name) {
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(this.toColor(name));
        this.setItemMeta(itemMeta);

        return this;
    }

    public void setItemMeta(ItemMeta itemMeta) {
        this.itemStack.setItemMeta(itemMeta);
    }

    public ItemMeta getItemMeta() {
        return this.itemStack.getItemMeta();
    }

    public Item(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack bukkitItem() {
        return itemStack;
    }
}
