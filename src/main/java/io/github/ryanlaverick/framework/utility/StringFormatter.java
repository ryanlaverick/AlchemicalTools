package io.github.ryanlaverick.framework.utility;

import org.bukkit.ChatColor;

public final class StringFormatter {
    public static String translateColorCodes(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
