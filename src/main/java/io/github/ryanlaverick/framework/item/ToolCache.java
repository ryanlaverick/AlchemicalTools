package io.github.ryanlaverick.framework.item;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.item.Tool;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class ToolCache {
    private final AlchemicalTools alchemicalTools;
    private final Map<Tool, ItemStack> cache;

    public ToolCache(AlchemicalTools alchemicalTools) {
        this.cache = new EnumMap<>(Tool.class);
        this.alchemicalTools = alchemicalTools;

        for (Tool tool : Tool.values()) {
            this.cache.put(tool, new ToolBuilder(tool, this.alchemicalTools).buildTool());
        }
    }

    public ItemStack getItemStack(Tool tool) {
        return this.cache.get(tool);
    }
}
