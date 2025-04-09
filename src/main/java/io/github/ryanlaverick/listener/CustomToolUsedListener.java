package io.github.ryanlaverick.listener;

import io.github.ryanlaverick.event.CustomToolUsedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public final class CustomToolUsedListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onUse(CustomToolUsedEvent event) {
        if (event.isCancelled()) return;

        Player player = event.getPlayer();
        player.sendMessage("Used Tool!");
    }
}
