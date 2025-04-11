package io.github.ryanlaverick.framework.effect;

import io.github.ryanlaverick.AlchemicalTools;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public final class EffectProfile {
    private boolean shouldPlayEffect;
    private Effect effect = null;

    public EffectProfile(AlchemicalTools alchemicalTools, FileConfiguration configurationFile) {
        this.shouldPlayEffect = configurationFile.getBoolean("options.effect.plays_effect");

        if (! this.shouldPlayEffect) {
            return;
        }

        String effectString = configurationFile.getString("options.effect.effect");

        try {
            this.effect = Effect.valueOf(effectString);
        } catch (IllegalArgumentException|NullPointerException ignored) {
            alchemicalTools.getLogger().severe("Unable to parse Effect {effect}".replace("{effect}", effectString));
            this.shouldPlayEffect = false;
        }
    }

    public void play(Player player, Location location) {
        if (! this.shouldPlayEffect) {
            return;
        }

        player.playEffect(location, this.effect, null);
    }
}
