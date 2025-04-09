package io.github.ryanlaverick;

import io.github.ryanlaverick.command.AlchemicalToolsCommand;
import io.github.ryanlaverick.framework.file.ToolFileCache;
import io.github.ryanlaverick.listener.CustomToolUsedListener;
import io.github.ryanlaverick.listener.TriggerCustomToolUsedListener;
import org.bukkit.plugin.java.JavaPlugin;

public class AlchemicalTools extends JavaPlugin {
    private ToolFileCache toolFileCache;

    @Override
    public void onEnable() {
        this.getLogger().info("Plugin Enabled");

        this.getServer().getPluginManager().registerEvents(new TriggerCustomToolUsedListener(), this);
        this.getServer().getPluginManager().registerEvents(new CustomToolUsedListener(), this);

        this.getCommand("alchemicaltools").setExecutor(new AlchemicalToolsCommand());

        this.toolFileCache = new ToolFileCache(this);
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Plugin Disabled");
    }

    public ToolFileCache getToolFileCache() {
        return toolFileCache;
    }
}
