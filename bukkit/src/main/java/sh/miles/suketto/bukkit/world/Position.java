package sh.miles.suketto.bukkit.world;

import com.google.common.base.Preconditions;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A Position is like a {@link Location} but it is not coupled to a world. The focus of this class is lightweight data
 * storage and additions subtraction etc.
 */
public record Position(int x, int y, int z) {

    /**
     * Adds the given x, y and z values and returns a position with the changed values
     *
     * @param x the x
     * @param y the y
     * @param z the z
     * @return a new Position with the added values
     */
    @NotNull
    public Position add(final int x, final int y, final int z) {
        return new Position(x() + x, y() + y, z() + z);
    }

    /**
     * Subtracts the given x, y and z values and returns a position with the changed values
     *
     * @param x the x
     * @param y the y
     * @param z the z
     * @return a new Position with the subtracted values
     */
    @NotNull
    public Position subtract(final int x, final int y, final int z) {
        return new Position(x() - x, y() - y, z() - z);
    }

    /**
     * Multiplies the given x, y and z values and returns a position with the changed values
     *
     * @param x the x
     * @param y the y
     * @param z the z
     * @return a new Position with the multiplied values
     */
    @NotNull
    public Position multiply(final int x, final int y, final int z) {
        return new Position(x() * x, y() * y, z() * z);
    }

    /**
     * Divides the given x, y and z values and returns a position with the changed values
     *
     * @param x the x
     * @param y the y
     * @param z the z
     * @return a new Position with the divided values
     */
    @NotNull
    public Position divide(final int x, final int y, final int z) {
        return new Position(x() / x, y() / y, z() / z);
    }

    /**
     * Creates a new location from the position with the provided world
     *
     * @param world the world to have the position bound to
     * @return a Location bound to the provided world
     */
    @NotNull
    public Location location(@NotNull final World world) {
        Preconditions.checkNotNull(world);

        return new Location(world, x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return x == position.x && y == position.y && z == position.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}
