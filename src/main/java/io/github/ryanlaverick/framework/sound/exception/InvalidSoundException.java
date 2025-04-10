package io.github.ryanlaverick.framework.sound.exception;

public final class InvalidSoundException extends RuntimeException {
    public InvalidSoundException(String invalidSound) {
        super("Unable to parse Sound {sound}".replace("{sound}", invalidSound));
    }
}
