package io.github.ryanlaverick.framework.file;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.framework.item.Tool;

import java.util.EnumMap;
import java.util.Map;

public final class ToolFileCache {
    private final Map<Tool, ToolFile> files;

    public ToolFileCache(AlchemicalTools alchemicalTools) {
        this.files = new EnumMap<>(Tool.class);

        for (Tool tool : Tool.values()) {
            this.files.put(tool, new ToolFile(tool, alchemicalTools));
        }
    }

    public Map<Tool, ToolFile> getFiles() {
        return files;
    }

    public ToolFile getFile(Tool tool) {
        return this.files.get(tool);
    }
}
