package io.github.ryanlaverick.framework.sound;

import io.github.ryanlaverick.framework.item.Tool;
import io.github.ryanlaverick.framework.sound.exception.InvalidSoundException;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public final class SoundProfile {
    private final boolean shouldPlaySound;
    private Sound sound = null;
    private float pitch = 1.0F;
    private float volume = 1.0F;

    public SoundProfile(FileConfiguration configurationFile) {
        this.shouldPlaySound = configurationFile.getBoolean("options.sound.plays_sound");

        if (! this.shouldPlaySound) {
            return;
        }

        try {
            this.sound = Sound.valueOf(configurationFile.getString("options.sound.sound"));
        } catch (IllegalArgumentException|NullPointerException ignored) {
            throw new InvalidSoundException(configurationFile.getString("options.sound.sound"));
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
