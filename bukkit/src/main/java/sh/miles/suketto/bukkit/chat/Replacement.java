package sh.miles.suketto.bukkit.chat;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * key value pair that replaces in the target string
 *
 * @param key   the key
 * @param value the value
 */
public record Replacement(String key, Object value) {

    /**
     * Replaces the key with teh value on the target
     *
     * @param target the target to run the replacement on
     * @return the replaced string
     */
    public String replace(String target) {
        return target.replace(wrap(key), value.toString());
    }

    /**
     * Reverse the replacement on the target string
     *
     * @param target the string to reverse the replacement on
     * @return the fixed string
     */
    public String reverse(String target) {
        return target.replace(value.toString(), wrap(key));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Replacement that)) return false;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "Replacement{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }

    /**
     * Wraps the target in prefix and suffix
     *
     * @param target target string to be wrapped
     * @return the wrapped string
     */
    private static String wrap(String target) {
        return '{' + target + '}';
    }

    public static Replacement of(@NotNull final String key, @NotNull final Object value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);

        return new Replacement(key, value);
    }
}
