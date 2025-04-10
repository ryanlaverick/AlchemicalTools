package io.github.ryanlaverick;

import io.github.ryanlaverick.command.AlchemicalToolsCommand;
import io.github.ryanlaverick.framework.file.ToolFileCache;
import io.github.ryanlaverick.framework.item.ToolCache;
import io.github.ryanlaverick.handler.SmeltersPickaxeHandler;
import io.github.ryanlaverick.listener.TriggerCustomToolUsedListener;
import org.bukkit.plugin.java.JavaPlugin;

public class AlchemicalTools extends JavaPlugin {
    private ToolFileCache toolFileCache;
    private ToolCache toolCache;

    @Override
    public void onEnable() {
        this.getLogger().info("Plugin Enabled");

        this.toolFileCache = new ToolFileCache(this);
        this.toolCache = new ToolCache(this);

        this.getServer().getPluginManager().registerEvents(new TriggerCustomToolUsedListener(this), this);
        this.getServer().getPluginManager().registerEvents(new SmeltersPickaxeHandler(this), this);

        this.getCommand("alchemicaltools").setExecutor(new AlchemicalToolsCommand(this));
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Plugin Disabled");
    }

    public ToolFileCache getToolFileCache() {
        return toolFileCache;
    }

    public ToolCache getToolCache() {
        return toolCache;
    }
}
