package sh.miles.suketto.core.collection.hold;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Much like the {@link Tuple} this collection contains 2 items of different types. However, unlike a {@link Tuple} a
 * {@link Pair} is mutable.
 *
 * @param <A> The first type.
 * @param <B> The second Type
 */
public class Pair<A, B> {

    private A first;
    private B second;

    /**
     * Provides a new instance of a Pair with the provided first and second element.
     *
     * @param first  The first element of the pair.
     * @param second The second element of the pair.
     */
    public Pair(@Nullable final A first, @Nullable final B second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Provides the first element of the pair.
     *
     * @return The first element of the pair if it exists otherwise null.
     */
    @Nullable
    public A getFirst() {
        return this.first;
    }

    /**
     * Provides the second element of the pair.
     *
     * @return The first element of the pair if it exists otherwise null.
     */
    @Nullable
    public B getSecond() {
        return this.second;
    }

    /**
     * Sets the first element in the pair.
     *
     * @param first The object to put in the first element.
     */
    public void setFirst(A first) {
        this.first = first;
    }

    /**
     * Sets the second element in the pair.
     *
     * @param second The object to put in the second element.
     */
    public void setSecond(B second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?, ?> pair)) return false;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" + "first=" + first + ", second=" + second + '}';
    }
}
