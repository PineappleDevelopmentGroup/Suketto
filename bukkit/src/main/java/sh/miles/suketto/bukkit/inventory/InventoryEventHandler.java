package sh.miles.suketto.bukkit.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Handles Inventory Events
 */
public interface InventoryEventHandler {

    /**
     * Handles all click events going towards this InventoryHandler
     *
     * @param event the {@link InventoryClickEvent}
     */
    void handleClick(@NotNull final InventoryClickEvent event);

    /**
     * Handles all open events going towards this InventoryHandler
     *
     * @param event the {@link InventoryOpenEvent}
     */
    void handleOpen(@NotNull final InventoryOpenEvent event);

    /**
     * Handles all close events going towards this InventoryHandler
     *
     * @param event the {@link InventoryCloseEvent}
     */
    void handleClose(@NotNull final InventoryCloseEvent event);

}
