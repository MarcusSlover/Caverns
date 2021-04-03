package me.marcusslover.caverns.game.item;

import me.marcusslover.caverns.api.utils.IManager;

import java.util.ArrayList;
import java.util.List;

public class ItemManager implements IManager<GameItem> {
    private static ItemManager instance;
    private final List<GameItem> gameItems;

    private ItemManager() {
        instance = this;
        this.gameItems = new ArrayList<>();
        initialize();
    }

    @Override
    public void initialize() {
    }

    @Override
    public void register(GameItem value) {
        gameItems.add(value);
    }

    @Override
    public List<GameItem> getAll() {
        return gameItems;
    }

    public static ItemManager getInstance() {
        return instance == null ? new ItemManager() : instance;
    }
}
