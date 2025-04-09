package io.github.ryanlaverick.framework.file;

import io.github.ryanlaverick.AlchemicalTools;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public abstract class CustomFile {
    protected FileConfiguration fileConfiguration;

    protected CustomFile(String name, String directory, AlchemicalTools alchemicalTools) {
        File file = new File(alchemicalTools.getDataFolder(), directory + File.separator + name);

        if (! file.exists()) {
            file.getParentFile().mkdirs();
            alchemicalTools.saveResource(directory + File.separator + name, false);
        }

        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }
}
