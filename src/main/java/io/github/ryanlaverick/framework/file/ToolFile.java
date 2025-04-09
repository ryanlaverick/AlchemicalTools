package io.github.ryanlaverick.framework.file;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.item.Tool;

import java.io.File;

public final class ToolFile extends CustomFile {
    public ToolFile(Tool tool, AlchemicalTools alchemicalTools) {
        super(tool.getName() + ".yml", "tools", alchemicalTools);
    }
}
