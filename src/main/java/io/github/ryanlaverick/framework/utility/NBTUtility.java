package io.github.ryanlaverick.framework.utility;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.framework.item.NBTKey;
import io.github.ryanlaverick.framework.item.Tool;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;

public final class NBTUtility {
    public final AlchemicalTools alchemicalTools;
    public NBTUtility(AlchemicalTools alchemicalTools) {
        this.alchemicalTools = alchemicalTools;
    }

    public boolean hasNBTKey(ItemStack itemStack, NBTKey key)
    {
        if (! itemStack.hasItemMeta()) {
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        for (NamespacedKey namespacedKey : persistentDataContainer.getKeys()) {
            if (namespacedKey.getKey().equalsIgnoreCase(key.getKey())) {
                return true;
            }
        }

        return false;
    }

    public Tool getToolFromItemStack(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        return Tool.tryFrom(persistentDataContainer.get(
                new NamespacedKey(this.alchemicalTools, NBTKey.ALCHEMICAL_TOOLS_TYPE.getKey()),
                PersistentDataType.STRING
        ));
    }
}
