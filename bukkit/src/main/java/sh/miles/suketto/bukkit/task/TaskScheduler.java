package sh.miles.suketto.bukkit.task;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

/**
 * Contains scheduler options for both sync and async operations based on bukkit thread and independent thread
 */
public final class TaskScheduler {

    private final AsyncTaskScheduler async;
    private final SyncTaskScheduler sync;

    public TaskScheduler(@NotNull final Plugin plugin) {
        this.async = new AsyncTaskScheduler(plugin);
        this.sync = new SyncTaskScheduler(plugin);
    }

    public AsyncTaskScheduler async() {
        return async;
    }

    public SyncTaskScheduler sync() {
        return sync;
    }

    /**
     * Contains Schedulers for sync operations
     */
    private static final class SyncTaskScheduler {


        private final Plugin plugin;

        SyncTaskScheduler(@NotNull final Plugin plugin) {
            this.plugin = plugin;
        }

        /**
         * Runs a task on the main thread without a delay
         *
         * @param runnable the function to run
         * @return a bukkit task created from the {@link BukkitScheduler}
         */
        public BukkitTask runTask(@NotNull final Runnable runnable) {
            return Bukkit.getScheduler().runTask(plugin, runnable);
        }

        /**
         * Runs a task with a fixed delay
         *
         * @param runnable the function to run
         * @param delay    the delay
         * @param unit     the time unit the delay is in
         * @return a bukkit task created from the {@link BukkitScheduler}
         */
        public BukkitTask runTaskLater(@NotNull final Runnable runnable, final long delay, @NotNull final TaskUnit unit) {
            return Bukkit.getScheduler().runTaskLater(plugin, runnable, unit.toTicks(delay));
        }

        /**
         * Runs a task timer with a starting delay at a repeating rate
         *
         * @param runnable the function to run
         * @param delay    the delay
         * @param period   the period
         * @param unit     the time unit the delay is in
         * @return a bukkit task created from the {@link BukkitScheduler}
         */
        public BukkitTask runTaskTimer(@NotNull final Runnable runnable, final long delay, final long period, @NotNull final TaskUnit unit) {
            return Bukkit.getScheduler().runTaskTimer(plugin, runnable, unit.toTicks(delay), unit.toTicks(period));
        }


    }

    /**
     * Contains Schedulers for async operations
     */
    private static final class AsyncTaskScheduler {

        private final Plugin plugin;

        AsyncTaskScheduler(@NotNull final Plugin plugin) {
            this.plugin = plugin;
        }

        /**
         * Runs a task on the main thread without a delay
         *
         * @param runnable the function to run
         * @return a bukkit task created from the {@link BukkitScheduler}
         */
        public BukkitTask runTask(@NotNull final Runnable runnable) {
            return Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
        }

        /**
         * Runs a task with a fixed delay
         *
         * @param runnable the function to run
         * @param delay    the delay
         * @param unit     the time unit the delay is in
         * @return a bukkit task created from the {@link BukkitScheduler}
         */
        public BukkitTask runTaskLater(@NotNull final Runnable runnable, final long delay, @NotNull final TaskUnit unit) {
            return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, unit.toTicks(delay));
        }

        /**
         * Runs a task timer with a starting delay at a repeating rate
         *
         * @param runnable the function to run
         * @param delay    the delay
         * @param period   the period
         * @param unit     the time unit the delay is in
         * @return a bukkit task created from the {@link BukkitScheduler}
         */
        public BukkitTask runTaskTimer(@NotNull final Runnable runnable, final long delay, final long period, @NotNull final TaskUnit unit) {
            return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, unit.toTicks(delay), unit.toTicks(period));
        }

    }

}
