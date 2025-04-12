package io.github.ryanlaverick.framework.utility;

import java.util.concurrent.ThreadLocalRandom;

public final class NumberUtility {
    private NumberUtility() {}

    public static int getRandomNumber() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int getRandomNumber(int boundary) {
        return ThreadLocalRandom.current().nextInt(boundary);
    }
}
