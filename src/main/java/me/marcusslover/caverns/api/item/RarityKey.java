package me.marcusslover.caverns.api.item;

import me.marcusslover.caverns.api.utils.ColorUtil;

public enum RarityKey {
    COMMON("FCFCFC"),
    UNCOMMON("B0FCB3"),
    RARE("B0F4FC"),
    EPIC("D9B0FC"),
    LEGENDARY("FCEDB0"),
    MYTHIC("FCB0EC"),
    GODLY("FCB0B0"),
    OTHERWORLDLY("B6B0FC");

    private final String color;

    RarityKey(String color) {
        this.color = ColorUtil.toColor("%hex(#"+color+")");
    }

    public String getColor() {
        return color;
    }
}
