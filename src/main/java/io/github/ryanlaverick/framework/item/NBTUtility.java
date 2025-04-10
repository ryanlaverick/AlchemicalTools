package io.github.ryanlaverick.framework.item;

import io.github.ryanlaverick.AlchemicalTools;
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
}
