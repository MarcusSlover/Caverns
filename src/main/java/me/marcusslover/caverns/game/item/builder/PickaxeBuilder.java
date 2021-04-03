package me.marcusslover.caverns.game.item.builder;

import me.marcusslover.caverns.api.item.Item;
import me.marcusslover.caverns.game.item.Pickaxe;
import org.bukkit.Material;

public class PickaxeBuilder extends BasicItemBuilder implements ItemBuilder<Pickaxe> {

    private int speed = 0;
    private int pierce = 0;
    private int fortune = 0;
    private int outreach = 0;

    PickaxeBuilder(Material material) {
        super(material);
    }

    public PickaxeBuilder withMiningSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public PickaxeBuilder withPierce(int pierce) {
        this.pierce = pierce;
        return this;
    }

    public PickaxeBuilder withFortune(int fortune) {
        this.fortune = fortune;
        return this;
    }

    public PickaxeBuilder withOutreach(int outreach) {
        this.outreach = outreach;
        return this;
    }

    @Override
    public Pickaxe buildItem() {
        String color = rarity.getColor();
        String name = color + this.name;
        Pickaxe pickaxe = new Pickaxe(new Item(material, 1, name));

        return null;
    }
}
