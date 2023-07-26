package sh.miles.suketto.nms;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Provides methods to make Inventory Titles components
 */
public interface SukettoInventory {

    /**
     * Opens an inventory for the provided HumanEntity
     *
     * @param humanEntity the human entity to open the inventory for
     * @return the InventoryView associated with the viewing
     */
    @Nullable
    InventoryView open(@NotNull final HumanEntity humanEntity);

    /**
     * Sets the title of the inventory
     *
     * @param title the title
     */
    void setTitle(BaseComponent[] title);

    /**
     * Gets the title of the Inventory
     *
     * @return the title
     */
    @NotNull
    BaseComponent[] getTitle();

    /**
     * Gets the Inventory associated with teh SukettoInventory
     *
     * @return an inventory
     */
    @NotNull
    Inventory getInventory();

}
