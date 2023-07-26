package sh.miles.suketto.bukkit.inventory.gui;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

/**
 * Should be implemented by classes derived from {@link AbstractGui} that need a refreshing or animated type action that
 * occurs on a constant time period while the gui is open
 */
public interface Refreshing {

    /**
     * Intended to be called when the task is executed
     *
     * @param player the player
     */
    void refresh(@NotNull final Player player);

    /**
     * The period in which the task occurs
     *
     * @return the period
     */
    int period();

    /**
     * The delay before the initial execution of the task
     *
     * @return the delay
     */
    int delay();

    /**
     * Retrieves the task which runs
     *
     * @return the task
     */
    BukkitTask task();
}
