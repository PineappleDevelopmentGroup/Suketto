package sh.miles.suketto.bukkit.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.internal.annotations.Required;
import sh.miles.suketto.bukkit.inventory.gui.AbstractGui;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages {@link InventoryEventHandler} instances
 */
@Required
public class InventoryManager {

    private final Map<Inventory, InventoryEventHandler> inventories = new HashMap<>();

    /**
     * Opens a specific {@link AbstractGui} for a player and registers it to the {@link this#inventories} map.
     *
     * @param player the player to open the Gui for
     * @param gui    the gui to open
     */
    public void open(@NotNull final Player player, AbstractGui gui) {
        register(gui.getSukettoInventory().getInventory(), gui);
        gui.open(player);
    }

    /**
     * Registers a new InventoryEventHandler to a specific inventory to be used in a variety of events
     *
     * @param inventory the inventory to be registered to
     * @param handler   the handler to register to the inventory
     */
    public void register(@NotNull final Inventory inventory, @NotNull final InventoryEventHandler handler) {
        this.inventories.put(inventory, handler);
    }

    /**
     * Unregisters Inventories from the inventories map. This method should be avoided in calls due to its ability to
     * easily break inventory session
     *
     * @param inventory the inventory to unregister
     */
    protected void unregister(@NotNull final Inventory inventory) {
        this.inventories.remove(inventory);
    }

    /**
     * Handles the click events in the Manager
     *
     * @param event the event
     */
    void handleClick(@NotNull final InventoryClickEvent event) {
        final InventoryEventHandler handler = this.inventories.get(event.getInventory());
        if (handler == null) {
            return;
        }

        handler.handleClick(event);
    }

    /**
     * Handles the open events in the Manager
     *
     * @param event the event
     */
    void handleOpen(@NotNull final InventoryOpenEvent event) {
        final InventoryEventHandler handler = this.inventories.get(event.getInventory());
        if (handler == null) {
            return;
        }

        handler.handleOpen(event);
    }

    /**
     * Handles the close events in the Manager
     *
     * @param event the event
     */
    void handleClose(@NotNull final InventoryCloseEvent event) {
        final InventoryEventHandler handler = this.inventories.get(event.getInventory());
        if (handler == null) {
            return;
        }

        handler.handleClose(event);
    }
}
