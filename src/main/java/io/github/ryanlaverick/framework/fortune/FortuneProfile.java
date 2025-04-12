package io.github.ryanlaverick.framework.fortune;

import io.github.ryanlaverick.AlchemicalTools;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public final class FortuneProfile {
    private final Map<Material, FortuneSet> fortuneMap;

    public FortuneProfile(AlchemicalTools alchemicalTools, FileConfiguration configurationFile) {
        this.fortuneMap = new EnumMap<>(Material.class);

        if (configurationFile.isConfigurationSection("options.fortune")) {
            Set<String> entries = configurationFile.getConfigurationSection("options.fortune").getKeys(false);

            for (String materialName : entries) {
                Material matchedMaterial = Material.matchMaterial(materialName);

                if (matchedMaterial == null) {
                    alchemicalTools.getLogger().severe(
                            "ERROR: Unable to map Material {material} for Fortune in {file}!"
                                    .replace("{material}", materialName)
                                    .replace("{file}", configurationFile.getName())
                    );

                    continue;
                }

                if (this.fortuneMap.containsKey(matchedMaterial)) {
                    alchemicalTools.getLogger().severe(
                            "ERROR: Unable to map Material {material} for Fortune in file {file}, it has already been mapped"
                                    .replace("{material}", materialName)
                                    .replace("{file}", configurationFile.getName())
                    );

                    continue;
                }

                this.fortuneMap.put(matchedMaterial, new FortuneSet(
                        configurationFile.getInt("options.fortune." + materialName + ".base_drop_amount"),
                        configurationFile.getInt("options.fortune." + materialName + ".minimum_drop_amount"),
                        configurationFile.getInt("options.fortune." + materialName + ".maximum_drop_amount")
                ));
            }
        }
    }

    public int getDropsForMaterial(Material material, ItemStack tool) {
        if (! this.fortuneMap.containsKey(material)) {
            return 0;
        }

        return this.fortuneMap.get(material).getDropAmount(tool);
    }
}
