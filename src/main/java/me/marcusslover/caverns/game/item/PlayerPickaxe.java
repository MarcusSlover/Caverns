package me.marcusslover.caverns.game.item;

import me.marcusslover.caverns.api.item.Item;
import net.minecraft.server.v1_16_R3.NBTTagCompound;

public class PlayerPickaxe {
    private final Item item;

    public PlayerPickaxe(Item item) {
        this.item = item;
    }

    public void setBlocksBroken(long blocksBroken) {
        NBTTagCompound tag = item.getTag();
        tag.setLong("blocksBroken", blocksBroken);
    }

    public long getBlocksBroken() {
        NBTTagCompound tag = item.getTag();
        return tag.getLong("blocksBroken");
    }

    public Item getItem() {
        return item;
    }
}
