package sh.miles.suketto.core.collection.list;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.core.collection.abstracts.WeightedCollection;
import sh.miles.suketto.core.collection.hold.Tuple;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * An Implementation of {@link WeightedCollection} into a list like data structure.
 *
 * @param <E> The type of the list.
 */
public class WeightedList<E> implements WeightedCollection<E> {

    private final NavigableMap<Double, E> map;
    private final SecureRandom random;
    private double total;

    /**
     * An internal constructor for creating a WeightedList
     *
     * @param map    the navigable map
     * @param random a random instance
     * @param total  total
     */
    private WeightedList(@NotNull final NavigableMap<Double, E> map, @NotNull final SecureRandom random, final double total) {
        this.map = map;
        this.random = random;
        this.total = total;
    }

    public WeightedList() {
        this(new TreeMap<>(), new SecureRandom(SecureRandom.getSeed(96)), 0);
    }

    @Override
    public void add(final double weight, @NotNull final E entry) {
        if (weight <= 0) {
            throw new IllegalArgumentException("weights entered into a weighted list must be greater than 0");
        }
        total += weight;
        map.put(total, entry);
    }

    @Nullable
    @Override
    public E next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }

    @NotNull
    @Override
    public Collection<E> next(final int rolls) {
        final List<E> list = new ArrayList<>();
        for(int i = 0; i < rolls; i++){
            list.add(next());
        }

        return list;

    }
}
