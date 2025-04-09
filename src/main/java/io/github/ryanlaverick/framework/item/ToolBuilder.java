package io.github.ryanlaverick.framework.item;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.framework.file.ToolFile;
import io.github.ryanlaverick.framework.utility.StringFormatter;
import io.github.ryanlaverick.item.Tool;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ToolBuilder {
    private final Tool tool;
    private final AlchemicalTools alchemicalTools;
    private final FileConfiguration toolFile;

    public ToolBuilder(Tool tool, AlchemicalTools alchemicalTools) {
        this.tool = tool;
        this.alchemicalTools = alchemicalTools;

        this.toolFile = this.alchemicalTools.getToolFileCache().getFile(this.tool).getFileConfiguration();
    }

    public ItemStack buildTool() {
        Material material = Material.getMaterial(this.toolFile.getString("item.material"));
        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringFormatter.translateColorCodes(this.toolFile.getString("item.display_name")));

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
