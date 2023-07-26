package sh.miles.suketto.bukkit.inventory.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Base Interface for all buttons
 */
public interface Button {

    /**
     * Gets the icon of the button
     *
     * @return the ItemStack Icon
     */
    ItemStack icon();

    /**
     * Triggered on click
     *
     * @param event the event
     */
    void click(@NotNull final InventoryClickEvent event);

}
