package me.marcusslover.caverns.game.item;

import me.marcusslover.caverns.api.item.Item;
import net.minecraft.server.v1_16_R3.NBTTagCompound;

public class Pickaxe extends GameItem {

    public Pickaxe(Item item) {
        super(item);
    }

    public int getPierce() {
        NBTTagCompound tag = item.getTag();
        return tag.getInt("pierce");
    }

    public int getMiningSpeed() {
        NBTTagCompound tag = item.getTag();
        return tag.getInt("miningSpeed");
    }

    public Item getItem() {
        return item;
    }
}
