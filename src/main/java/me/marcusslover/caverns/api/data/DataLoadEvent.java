package me.marcusslover.caverns.api.data;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class DataLoadEvent extends PlayerEvent {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private final PlayerContainer playerContainer;

    public DataLoadEvent(Player who, PlayerContainer playerContainer) {
        super(who);
        this.playerContainer = playerContainer;
    }

    public PlayerContainer getPlayerContainer() {
        return playerContainer;
    }

    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
