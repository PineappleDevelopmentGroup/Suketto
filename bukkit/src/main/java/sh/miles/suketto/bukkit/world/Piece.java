package sh.miles.suketto.bukkit.world;

import com.google.common.base.Preconditions;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A Piece represents a world free implementation of the chunk {@link Chunk} with utility methods offering conversion.
 * It's intended use is for lightweight data storage. But it can also be implemented for other reasons
 */
public record Piece(int x, int z) {

    /**
     * Adds the Piece with the given values and returns a new Piece
     *
     * @param x the value to add to x
     * @param z the value to add to y
     * @return a new piece with the modified values
     */
    public Piece add(final int x, final int z) {
        return new Piece(this.x + x, this.z + z);
    }

    /**
     * Subtracts the Piece with the given values and returns a new Piece
     *
     * @param x the value to subtract from x
     * @param z the value to subtract to y
     * @return a new piece with the modified values
     */
    public Piece subtract(final int x, final int z) {
        return new Piece(this.x - x, this.z - z);
    }

    /**
     * Multiplies the Piece with the given values and returns a new Piece
     *
     * @param x the value to multiply to x
     * @param z the value to multiply to y
     * @return a new piece with the modified values
     */
    public Piece multiply(final int x, final int z) {
        return new Piece(this.x * x, this.z * z);
    }

    /**
     * Divides the Piece with the given values and returns a new Piece
     *
     * @param x the value to divide from x
     * @param z the value to divide from y
     * @return a new piece with the modified values
     */
    public Piece divide(final int x, final int z) {
        return new Piece(this.x / x, this.z / z);
    }

    /**
     * Converts the given Piece into a {@link Chunk}
     *
     * @param world the world to bind the piece to
     * @return a chunk at the position of the piece
     */
    public Chunk chunk(@NotNull final World world) {
        Preconditions.checkNotNull(world);

        return world.getChunkAt(x, z);
    }

    @Override
    public String toString() {
        return "Piece{" + "x=" + x + ", z=" + z + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece piece)) return false;
        return x == piece.x && z == piece.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }
    
}
