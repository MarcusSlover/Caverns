package me.marcusslover.caverns.game.item.builder;

import me.marcusslover.caverns.game.item.GameItem;

public interface ItemBuilder<T extends GameItem> {
    T buildItem();
}
