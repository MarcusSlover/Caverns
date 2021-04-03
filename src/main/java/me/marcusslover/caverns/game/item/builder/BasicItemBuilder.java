package me.marcusslover.caverns.game.item.builder;

import me.marcusslover.caverns.api.item.RarityKey;
import org.bukkit.Material;

public class BasicItemBuilder {
    protected Material material;
    protected String name;
    protected RarityKey rarity;

    BasicItemBuilder(Material material) {
        this.material = material;
    }

    public BasicItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BasicItemBuilder withRarity(RarityKey rarity) {
        this.rarity = rarity;
        return this;
    }
}
