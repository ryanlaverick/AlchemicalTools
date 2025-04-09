package io.github.ryanlaverick.listener;

import io.github.ryanlaverick.event.CustomToolUsedEvent;
import io.github.ryanlaverick.item.NBTUtility;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public final class CustomToolUsedListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;

        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();

        if (itemInHand.getType().isAir()) return;

        Bukkit.getServer().getPluginManager().callEvent(new CustomToolUsedEvent(event.getPlayer(), itemInHand));
    }
}
