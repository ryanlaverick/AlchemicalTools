package io.github.ryanlaverick.framework.item;

public enum Tool {
    SMELTERS_PICKAXE("smelters_pickaxe");

    private final String name;

    Tool(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Tool tryFrom(String key) {
        for (Tool tools : Tool.values()) {
            if (tools.getName().equalsIgnoreCase(key)) {
                return tools;
            }
        }

        return null;
    }
}
