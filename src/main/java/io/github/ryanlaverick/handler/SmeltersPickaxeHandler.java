package io.github.ryanlaverick.handler;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.event.CustomToolUsedEvent;
import io.github.ryanlaverick.framework.effect.EffectProfile;
import io.github.ryanlaverick.framework.fortune.FortuneProfile;
import io.github.ryanlaverick.framework.item.Tool;
import io.github.ryanlaverick.framework.item.ToolHandler;
import io.github.ryanlaverick.framework.sound.SoundProfile;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public final class SmeltersPickaxeHandler extends ToolHandler {
    private final Map<Material, Material> materialMap;
    private SoundProfile soundProfile;
    private EffectProfile effectProfile;
    private FortuneProfile fortuneProfile;
    private boolean dropsToFloor = true;

    public SmeltersPickaxeHandler(AlchemicalTools alchemicalTools) {
        this.materialMap = new EnumMap<>(Material.class);

        this.load(alchemicalTools);
    }

    @Override
    public void load(AlchemicalTools alchemicalTools) {
        FileConfiguration toolFile = alchemicalTools.getToolFileCache().getFile(Tool.SMELTERS_PICKAXE).getFileConfiguration();
        if (toolFile.isConfigurationSection("conversions")) {
            Set<String> mappings = toolFile.getConfigurationSection("conversions").getKeys(false);

            for (String materialName : mappings) {
                Material actualMaterial = Material.matchMaterial(materialName);

                if (actualMaterial == null) {
                    alchemicalTools.getLogger().severe("ERROR: Unable to map Material {material} for Smelters Pickaxe!".replace("{material}", materialName));
                    continue;
                }

                if (this.materialMap.containsKey(actualMaterial)) {
                    alchemicalTools.getLogger().severe("ERROR: Unable to map Material {material} for Smelters Pickaxe as it has already been mapped!".replace("{material}", materialName));
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

        if (toolFile.isConfigurationSection("options")) {
            this.dropsToFloor = toolFile.getBoolean("options.drops_to_floor");

            this.soundProfile = new SoundProfile(alchemicalTools, toolFile);
            this.effectProfile = new EffectProfile(alchemicalTools, toolFile);
            this.fortuneProfile = new FortuneProfile(alchemicalTools, toolFile);
        }
    }

    @Override
    @EventHandler(priority = EventPriority.MONITOR)
    public void handleTool(CustomToolUsedEvent customToolUsedEvent) {
        if (customToolUsedEvent.isCancelled()) {
            return;
        }

        if (customToolUsedEvent.getTool() != Tool.SMELTERS_PICKAXE || ! (customToolUsedEvent.getTrigger() instanceof BlockBreakEvent blockBreakEvent)) {
            return;
        }

        Block block = blockBreakEvent.getBlock();
        World world = block.getWorld();

        if (! this.materialMap.containsKey(block.getType())) {
            return;
        }

        Player player = customToolUsedEvent.getPlayer();
        ItemStack triggeringItem = customToolUsedEvent.getTriggeringItem();

        Material material = this.materialMap.get(block.getType());

        int calculatedDropAmount = this.fortuneProfile.getDropsForMaterial(material, triggeringItem);
        if (calculatedDropAmount == 0) {
            calculatedDropAmount = 1;
        }

        ItemStack itemStack = new ItemStack(material, calculatedDropAmount);

        blockBreakEvent.setDropItems(false);

        this.soundProfile.play(player);
        this.effectProfile.play(player, block.getLocation());

        if (this.dropsToFloor) {
            world.dropItemNaturally(block.getLocation(), itemStack);
        } else {
            player.getInventory().addItem(itemStack);
        }
    }
}
