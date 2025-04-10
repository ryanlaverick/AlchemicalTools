package io.github.ryanlaverick.framework.item;

public enum NBTKey {
    ALCHEMICAL_TOOLS_TYPE("alchemical_tools_type");

    private final String key;

    NBTKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
