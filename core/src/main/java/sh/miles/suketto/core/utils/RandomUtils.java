package sh.miles.suketto.core.utils;

import java.security.SecureRandom;

/**
 * Utilities based on randomness
 */
public final class RandomUtils {

    private static final SecureRandom random = new SecureRandom(SecureRandom.getSeed(96));

    /**
     * Utility Class
     */
    private RandomUtils() {
    }

    /**
     * Generates a random number between min and max. The numbers are exclusive meaning min and max are excluded from
     * possible results.
     * <p>
     * if min is 1 and max is 3 between will always return 2, if min is 0 and max is 5 between will return between 1 and
     * 4
     *
     * @param min the minimum value exclusive
     * @param max the maximum value exclusive
     * @return a random number between the provided min and max
     */
    public static int between(final int min, final int max) {
        return random.nextInt(min, max);
    }

    /**
     * Generates a random number between min and max. The numbers are exclusive meaning min and max are excluded from
     * possible results.
     * <p>
     * if min is 0.1 and max is 0.3 between will always return numbers between 0.1 and 0.3 not inlcuding 0.3
     *
     * @param min the minimum value exclusive
     * @param max the maximum value exclusive
     * @return a random number between the provided min and max
     */
    public static double between(final double min, final double max) {
        return random.nextDouble(min, max);
    }

    /**
     * Generates a random number between min and max. The numbers are exclusive meaning min and max are excluded from
     * possible results.
     * <p>
     * if min is 1 and max is 3 between will always return 2, if min is 0 and max is 5 between will return between 1 and
     * 4
     *
     * @param min the minimum value exclusive
     * @param max the maximum value exclusive
     * @return a random number between the provided min and max
     */
    public static long between(final long min, final long max) {
        return random.nextLong(min, max);
    }

    /**
     * Produces a new secure random instance with a seed 8 bytes in length
     *
     * @return secure random instance
     */
    public static SecureRandom newRandom() {
        return newRandom(8);
    }

    /**
     * Produces a new secure random instance with a seed length of the provided number of bytes
     *
     * @param numBytes the number of bytes
     * @return secure random instance
     */
    public static SecureRandom newRandom(final int numBytes) {
        return new SecureRandom(SecureRandom.getSeed(numBytes));
    }

    /**
     * Provides a {@link SecureRandom} instance
     *
     * @return a {@link SecureRandom} instance created during this class' initialization
     */
    public static SecureRandom getRandom() {
        return RandomUtils.random;
    }
}
