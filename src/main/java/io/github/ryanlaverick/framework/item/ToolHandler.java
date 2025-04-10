package io.github.ryanlaverick.framework.item;

import io.github.ryanlaverick.event.CustomToolUsedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public abstract class ToolHandler implements Listener {
    @EventHandler
    public abstract void handleTool(CustomToolUsedEvent customToolUsedEvent);
}
