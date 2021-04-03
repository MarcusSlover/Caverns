package me.marcusslover.caverns.api.item;

import me.marcusslover.caverns.api.utils.ColorUtil;
import me.marcusslover.caverns.api.utils.IColorable;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

public class Item implements IColorable, Cloneable {
    ItemStack itemStack = null;

    public Item(Material material) {
        this(material, 0);
    }

    public Item(Material material, int amount) {
        this(new ItemStack(material, amount));
    }

    public Item(Material material, int amount, String name) {
        this(new ItemStack(material, amount));
        this.setName(name);
    }

    public Item(Material material, int amount, String name, List<String> lore) {
        this(new ItemStack(material, amount));
        this.setName(name);
        this.setLore(lore);
    }

    public Item(ItemStack itemStack) {
        this.itemStack = itemStack;
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

    public Item setSkull(Player player) {
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta instanceof SkullMeta) {
            SkullMeta meta = (SkullMeta) itemMeta;
            meta.setOwningPlayer(player);
            setItemMeta(meta);
        }
        return this;
    }

    public boolean isNamed(String name) {
        if (!isValid()) return false;
        if (!itemStack.hasItemMeta() && itemStack.getItemMeta() == null) return false;

        ItemMeta itemMeta = itemStack.getItemMeta();
        if (!itemMeta.hasDisplayName()) {
            return false;
        }
        String itemName = itemMeta.getDisplayName();
        return itemName.equalsIgnoreCase(ColorUtil.toColor(name));
    }

    public boolean hasTag(String key) {
        if (!isValid()) return false;
        net.minecraft.server.v1_16_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(this.itemStack);
        NBTTagCompound tag = itemStack.getTag();
        if (tag == null) return false;

        return tag.hasKey(key);
    }

    public Item setTag(NBTTagCompound nbtTagCompound) {
        net.minecraft.server.v1_16_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(this.itemStack);
        itemStack.setTag(nbtTagCompound);
        this.itemStack = CraftItemStack.asBukkitCopy(itemStack);
        return this;
    }

    public Item withTag(String key, boolean b) {
        NBTTagCompound tag = getTag();
        tag.setBoolean(key, b);
        return setTag(tag);
    }

    public NBTTagCompound getTag() {
        if (!isValid()) return null;
        net.minecraft.server.v1_16_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(this.itemStack);
        return itemStack.getTag();
    }

    public boolean isValid() {
        return itemStack != null && itemStack.getType() != Material.AIR;
    }

    public ItemStack bukkitItem() {
        return itemStack;
    }
    @Override
    public Item clone() {
        return new Item(this.itemStack.clone());
    }

}
