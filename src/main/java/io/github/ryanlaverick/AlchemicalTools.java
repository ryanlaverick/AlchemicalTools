package io.github.ryanlaverick;

import io.github.ryanlaverick.command.AlchemicalToolsCommand;
import io.github.ryanlaverick.listener.CustomToolUsedListener;
import io.github.ryanlaverick.listener.TriggerCustomToolUsedListener;
import org.bukkit.plugin.java.JavaPlugin;

public class AlchemicalTools extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("Plugin Enabled");

        this.getServer().getPluginManager().registerEvents(new TriggerCustomToolUsedListener(), this);
        this.getServer().getPluginManager().registerEvents(new CustomToolUsedListener(), this);

        this.getCommand("alchemicaltools").setExecutor(new AlchemicalToolsCommand());
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Plugin Disabled");
    }
}
