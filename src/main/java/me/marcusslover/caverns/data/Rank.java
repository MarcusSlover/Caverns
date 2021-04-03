package me.marcusslover.caverns.data;

import me.marcusslover.caverns.utils.ColorUtil;

public class Rank {
    private static int globalPriority = 100;

    public final String name;
    public final String prefix;
    public final String chatColor;
    public final int priority;

    public Rank(String name, String prefix, String chatColor) {

        this.name = name;
        this.prefix = ColorUtil.toColor(prefix);
        this.chatColor = ColorUtil.toColor(chatColor);
        this.priority = globalPriority;
        globalPriority--;
    }
}
