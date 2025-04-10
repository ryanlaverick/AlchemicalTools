package io.github.ryanlaverick.framework.item;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.framework.file.ToolFile;
import io.github.ryanlaverick.framework.utility.StringFormatter;
import io.github.ryanlaverick.item.Tool;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public final class ToolBuilder {
    private final Tool tool;
    private final AlchemicalTools alchemicalTools;
    private final FileConfiguration toolFile;

    private NamespacedKey toolTypeKey;

    public ToolBuilder(Tool tool, AlchemicalTools alchemicalTools) {
        this.tool = tool;
        this.alchemicalTools = alchemicalTools;

        this.toolTypeKey = new NamespacedKey(alchemicalTools, NBTKey.ALCHEMICAL_TOOLS_TYPE.getKey());
        this.toolFile = this.alchemicalTools.getToolFileCache().getFile(this.tool).getFileConfiguration();
    }

    public ItemStack buildTool() {
        Material material = Material.getMaterial(this.toolFile.getString("item.material"));
        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore = new ArrayList<>();
        for (String loreLine : this.toolFile.getStringList("item.lore")) {
            lore.add(StringFormatter.translateColorCodes(loreLine));
        }

        itemMeta.setDisplayName(StringFormatter.translateColorCodes(this.toolFile.getString("item.display_name")));
        itemMeta.setLore(lore);

        if (this.toolFile.getBoolean("item.flags.unbreakable")) {
            itemMeta.setUnbreakable(true);
        }

        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        persistentDataContainer.set(this.toolTypeKey, PersistentDataType.STRING, this.tool.getName());

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
