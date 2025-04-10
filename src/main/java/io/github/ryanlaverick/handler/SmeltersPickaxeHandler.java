package io.github.ryanlaverick.handler;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.event.CustomToolUsedEvent;
import io.github.ryanlaverick.framework.item.Tool;
import io.github.ryanlaverick.framework.item.ToolHandler;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public final class SmeltersPickaxeHandler extends ToolHandler {
    private final Map<Material, Material> materialMap;

    public SmeltersPickaxeHandler(AlchemicalTools alchemicalTools) {
        this.materialMap = new EnumMap<>(Material.class);
        FileConfiguration toolFile = alchemicalTools.getToolFileCache().getFile(Tool.SMELTERS_PICKAXE).getFileConfiguration();

        if (toolFile.isConfigurationSection("conversions")) {
            Set<String> mappings = toolFile.getConfigurationSection("conversions").getKeys(false);

            for (String materialName : mappings) {
                Material actualMaterial = Material.matchMaterial(materialName);

                if (actualMaterial == null) {
                    alchemicalTools.getLogger().severe("ERROR: Unable to map Material {material} for Smelters Pickaxe!".replace("{material}", materialName));
                    continue;
                }

                String mappedTo = toolFile.getString("conversions." + materialName + ".to");
                Material mappedMaterial = Material.matchMaterial(mappedTo);

                if (mappedMaterial == null) {
                    alchemicalTools.getLogger().severe("ERROR: Unable to map Material {material} for Smelters Pickaxe!".replace("{material}", mappedTo));
                    continue;
                }

                this.materialMap.put(actualMaterial, mappedMaterial);
            }
        }
    }

    @Override
    @EventHandler
    public void handleTool(CustomToolUsedEvent customToolUsedEvent) {
        Player player = customToolUsedEvent.getPlayer();
        player.sendMessage("Consuming event for custom tool used...");

        if (customToolUsedEvent.isCancelled()) {
            player.sendMessage("Cancelled");
            return;
        }

        if (customToolUsedEvent.getTool() != Tool.SMELTERS_PICKAXE || ! (customToolUsedEvent.getTrigger() instanceof BlockBreakEvent)) {
            player.sendMessage("Tool not smelters pickaxe, or item is not block break event");
            return;
        }

        BlockBreakEvent blockBreakEvent = (BlockBreakEvent) customToolUsedEvent.getTrigger();
        Block block = blockBreakEvent.getBlock();
        World world = block.getWorld();

        if (! this.materialMap.containsKey(block.getType())) {
            player.sendMessage("Material is not mapped");
            return;
        }

        Material material = this.materialMap.get(block.getType());
        ItemStack itemStack = new ItemStack(material);

        blockBreakEvent.setDropItems(false);
        world.dropItemNaturally(block.getLocation(), itemStack);
    }
}
