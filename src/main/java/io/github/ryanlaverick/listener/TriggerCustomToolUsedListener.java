package io.github.ryanlaverick.listener;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.event.CustomToolUsedEvent;
import io.github.ryanlaverick.framework.item.Tool;
import io.github.ryanlaverick.framework.utility.NBTUtility;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public final class TriggerCustomToolUsedListener implements Listener {
    private final NBTUtility nbtUtility;
    public TriggerCustomToolUsedListener(AlchemicalTools alchemicalTools) {
        this.nbtUtility = new NBTUtility(alchemicalTools);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;

        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();

        if (itemInHand.getType().isAir()) return;

        Tool tool = this.nbtUtility.getToolFromItemStack(itemInHand);

        if (tool == null) {
            return;
        }

        Bukkit.getServer().getPluginManager().callEvent(new CustomToolUsedEvent(event.getPlayer(), event, tool, itemInHand));
    }
}
