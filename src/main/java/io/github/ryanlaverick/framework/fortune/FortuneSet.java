package io.github.ryanlaverick.framework.fortune;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.framework.utility.NumberUtility;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public final class FortuneSet {
    private final int baseDropAmount;
    private final int minimumTheoreticalDrops;
    private final int maximumTheoreticalDrops;

    public FortuneSet(int baseDropAmount, int minimumTheoreticalDrops, int maximumTheoreticalDrops) {
        this.baseDropAmount = baseDropAmount;
        this.minimumTheoreticalDrops = minimumTheoreticalDrops;
        this.maximumTheoreticalDrops = maximumTheoreticalDrops;
    }

    public int getDropAmount(ItemStack itemStack) {
        if (! itemStack.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
            return this.getBaseDropAmount();
        }

        int fortuneLevel = itemStack.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);

        if (fortuneLevel == 0) {
            return this.getBaseDropAmount();
        }

        int randomDrops = NumberUtility.getRandomNumber(fortuneLevel + 2) - 1;
        int itemsToDrop = this.getBaseDropAmount() * (randomDrops + 1);

        if (itemsToDrop < this.getMinimumTheoreticalDrops()) {
            return this.getMinimumTheoreticalDrops();
        }

        return Math.min(itemsToDrop, this.getMaximumTheoreticalDrops());
    }

    public int getBaseDropAmount() {
        return baseDropAmount;
    }

    public int getMinimumTheoreticalDrops() {
        return minimumTheoreticalDrops;
    }

    public int getMaximumTheoreticalDrops() {
        return maximumTheoreticalDrops;
    }
}
