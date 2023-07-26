package sh.miles.suketto.core.collection.abstracts;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.core.collection.hold.Tuple;

import java.util.Collection;
import java.util.NavigableMap;

/**
 * Weighted Collection's hold the necessary methods for constructing a collection that allows the creation of a
 * collection that can be randomly drawn on based on weights.
 *
 * @param <E> The type of the collection.
 */
public interface WeightedCollection<E> {

    /**
     * Adds an element to the weighted collection.
     * <p>
     * All implementations of this class should implement a {@link NavigableMap} with types Double, and E. And create a
     * system to ensure there is no overlap between keys in the {@link NavigableMap}.
     *
     * @param weight The weight the entry object should be drawn at.
     * @param entry  The entry object.
     */
    void add(final double weight, @NotNull final E entry);

    /**
     * Provides a random selected item from the collection.
     *
     * @return The object returned from the collection at the weight or null.
     */
    @Nullable
    E next();

    /**
     * Provides a list of randomly selected items from the collection.
     *
     * @param rolls the number of times to roll the collection randomly.
     * @return a collection of objects that were rolled from the collection.
     */
    @NotNull
    Collection<E> next(final int rolls);


}
