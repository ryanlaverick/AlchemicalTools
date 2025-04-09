package io.github.ryanlaverick.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public final class CustomToolUsedEvent extends Event implements Cancellable {
    private final Player player;
    private final ItemStack triggeringItem;

    private boolean isCancelled;

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public CustomToolUsedEvent(Player player, ItemStack triggeringItem) {
        this.player = player;
        this.triggeringItem = triggeringItem;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getTriggeringItem() {
        return triggeringItem;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
