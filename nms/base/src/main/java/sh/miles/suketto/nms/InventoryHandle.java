package sh.miles.suketto.nms;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.inventory.SukettoInventory;

/**
 * Handles Inventory Interactions with NMS
 */
public interface InventoryHandle {

    /**
     * Creates a {@link SukettoInventory} which supports components, but must be opened using its own open method
     * {@link SukettoInventory#open(HumanEntity)} SukettoInventories only support CustomInventories currently
     *
     * @param holder the holder
     * @param size   the size
     * @param title  the title
     * @return a new SukettoInventory
     */
    SukettoInventory inventory(@NotNull final InventoryHolder holder, final int size, @NotNull final BaseComponent[] title);

    /**
     * Creates a {@link SukettoInventory} which supports components, but must be opened using its own open method
     * {@link SukettoInventory#open(HumanEntity)} SukettoInventories only support CustomInventories currently
     *
     * @param holder        the holder
     * @param inventoryType the inventory type
     * @param title         the title
     * @return a new SukettoInventory
     */
    SukettoInventory inventory(@NotNull final InventoryHolder holder, @NotNull final InventoryType inventoryType, @NotNull final BaseComponent[] title);
}
