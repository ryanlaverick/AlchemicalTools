package io.github.ryanlaverick.listener;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.event.CustomToolUsedEvent;
import io.github.ryanlaverick.framework.item.NBTKey;
import io.github.ryanlaverick.framework.item.NBTUtility;
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

        if (this.nbtUtility.hasNBTKey(itemInHand, NBTKey.ALCHEMICAL_TOOLS_TYPE)) {
            Bukkit.getServer().getPluginManager().callEvent(new CustomToolUsedEvent(event.getPlayer(), itemInHand));
        }
    }
}
