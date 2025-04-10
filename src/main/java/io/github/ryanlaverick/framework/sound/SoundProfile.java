package io.github.ryanlaverick.framework.sound;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.framework.item.Tool;
import io.github.ryanlaverick.framework.sound.exception.InvalidSoundException;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public final class SoundProfile {
    private boolean shouldPlaySound;
    private Sound sound = null;
    private float pitch = 1.0F;
    private float volume = 1.0F;

    public SoundProfile(AlchemicalTools alchemicalTools, FileConfiguration configurationFile) {
        this.shouldPlaySound = configurationFile.getBoolean("options.sound.plays_sound");

        if (! this.shouldPlaySound) {
            return;
        }

        String soundString = configurationFile.getString("options.sound.sound");

        try {
            this.sound = Sound.valueOf(soundString);
        } catch (IllegalArgumentException|NullPointerException ignored) {
            alchemicalTools.getLogger().severe("Unable to parse Sound {sound}".replace("{sound}", soundString));
            this.shouldPlaySound = false;
        }

        this.pitch = (float) configurationFile.getDouble("options.sound.pitch");
        this.volume = (float) configurationFile.getDouble("options.sound.volume");
    }

    public void play(Player player) {
        if (! this.shouldPlaySound) {
            return;
        }

        player.playSound(player, this.sound, this.volume, this.pitch);
    }
}
