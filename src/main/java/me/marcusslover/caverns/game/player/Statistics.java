package me.marcusslover.caverns.game.player;

import me.marcusslover.caverns.api.utils.ColorUtil;

public enum Statistics {
    WALK_SPEED("24a4c7", "⏩"),
    MINING_SPEED("e89c46", "⏩"),
    SWING_SPEED("f2e263", "⛏"),
    OUTREACH("8541fa", "❄"),
    PIERCE("fa4741", "☈"),
    FORTUNE("30f23d", "♧"),
    ;

    public final String color;
    public final String symbol;

    Statistics(String color, String symbol) {
        this.color = ColorUtil.toColor("%hex(#"+color+")");
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
