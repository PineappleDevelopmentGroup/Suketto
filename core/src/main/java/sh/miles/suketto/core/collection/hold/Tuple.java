package sh.miles.suketto.core.collection.hold;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A Collection that contains a pair of items of different types. This class is immutable and can not be modified
 * directly.
 *
 * @param <A> The first type.
 * @param <B> The second type.
 */
public class Tuple<A, B> {

    private final A first;
    private final B second;

    /**
     * Creates a new Tuple instance
     *
     * @param first  The first item in the tuple.
     * @param second The second item in the tuple.
     */
    public Tuple(@NotNull final A first, @NotNull final B second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Provides the first element in the tuple.
     *
     * @return the object contained in the first position of the tuple.
     */
    @NotNull
    public A getFirst() {
        return this.first;
    }

    /**
     * Provides the second element in the tuple.
     *
     * @return the object contained in the second position of the tuple.fzx
     */
    @NotNull
    public B getSecond() {
        return this.second;
    }

    /**
     * Sets the first element of the tuple.
     *
     * @param first The item to set in the first position of the tuple.
     * @return A copy of the tuple with the modified element.
     */
    @NotNull
    public Tuple<A, B> setFirst(A first) {
        return new Tuple<>(first, this.second);
    }

    /**
     * Sets the second element of the tuple.
     *
     * @param second The item to set in the second position of the tuple.
     * @return A copy of the tuple with the modified element.
     */
    @NotNull
    public Tuple<A, B> setSecond(B second) {
        return new Tuple<>(this.first, second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple<?, ?> tuple)) return false;
        return Objects.equals(first, tuple.first) && Objects.equals(second, tuple.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Tuple{" + "first=" + first + ", second=" + second + '}';
    }
}
