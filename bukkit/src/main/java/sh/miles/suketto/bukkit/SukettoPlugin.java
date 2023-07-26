package sh.miles.suketto.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.command.CommandRegistrar;
import sh.miles.suketto.bukkit.inventory.InventoryListener;
import sh.miles.suketto.bukkit.inventory.InventoryManager;
import sh.miles.suketto.bukkit.minecraft.nms.NMSHandle;
import sh.miles.suketto.bukkit.task.TaskScheduler;

import java.util.logging.Logger;

/**
 * {@inheritDoc}
 * <p>
 * Suketto provides extra features than the normal JavaPlugin
 */
public class SukettoPlugin extends JavaPlugin {

    private static SukettoPlugin plugin;

    // Inventory
    private InventoryManager inventoryManager;
    private InventoryListener inventoryListener;

    // Command
    private CommandRegistrar commandRegistrar;

    // Task
    private TaskScheduler taskScheduler;

    // NMS

    @Override
    public void onEnable() {
        plugin = this;

        // Inventory
        this.inventoryManager = new InventoryManager();
        Bukkit.getPluginManager().registerEvents(new InventoryListener(inventoryManager), this);

        // Command
        this.commandRegistrar = new CommandRegistrar();

        // Task
        taskScheduler = new TaskScheduler(this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        this.commandRegistrar.unregisterAll(this);

        plugin = null;
    }

    /**
     * Returns an instance of the plugin's inventory manager
     *
     * @return the inventory manager
     */
    @NotNull
    public InventoryManager inventoryManager() {
        return this.inventoryManager;
    }

    /**
     * Returns an instance of the plugin's command registrar
     *
     * @return the command registrar
     */
    @NotNull
    public CommandRegistrar commandRegistrar() {
        return this.commandRegistrar;
    }

    /**
     * Returns an instance of the plugin's task scheduler
     *
     * @return the task scheduler
     */
    @NotNull
    public TaskScheduler scheduler() {
        return this.taskScheduler;
    }

    /**
     * Returns an instance of the plugin logger
     *
     * @return the plugin logger
     */
    @NotNull
    public static Logger logger() {
        return SukettoPlugin.plugin.getLogger();
    }
}
