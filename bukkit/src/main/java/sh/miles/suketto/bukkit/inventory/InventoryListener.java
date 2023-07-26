package sh.miles.suketto.bukkit.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.internal.annotations.Required;

/**
 * Listener required for any Inventory Related Methods in this Library to work properly
 */
@Required
public class InventoryListener implements Listener {

    private final InventoryManager inventoryManager;

    public InventoryListener(@NotNull final InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    @EventHandler
    public void onClick(@NotNull final InventoryClickEvent event) {
        inventoryManager.handleClick(event);
    }

    @EventHandler
    public void onOpen(@NotNull final InventoryOpenEvent event) {
        inventoryManager.handleOpen(event);
    }

    @EventHandler
    public void onClose(@NotNull final InventoryCloseEvent event){
        inventoryManager.handleClose(event);
    }


}
