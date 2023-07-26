package sh.miles.suketto.bukkit.inventory.gui;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.event.inventory.InventoryType;
import sh.miles.suketto.bukkit.internal.annotations.NMS;
import sh.miles.suketto.bukkit.minecraft.nms.NMSHandle;
import sh.miles.suketto.nms.inventory.SukettoInventory;

/**
 * Represents GuiType's that can be created
 */
public enum GuiType {

    CHEST_9x1(9, InventoryType.CHEST),
    CHEST_9x2(18, InventoryType.CHEST),
    CHEST_9x3(27, InventoryType.CHEST),
    CHEST_9x4(36, InventoryType.CHEST),
    CHEST_9x5(45, InventoryType.CHEST),
    CHEST_9x6(54, InventoryType.CHEST),
    DISPENSER(9, InventoryType.DISPENSER),
    DROPPER(9, InventoryType.DISPENSER),
    BARREL(27, InventoryType.BARREL),
    HOPPER(5, InventoryType.HOPPER);

    private final int size;
    private final InventoryType inventoryType;

    GuiType(int size, InventoryType inventoryType) {
        this.size = size;
        this.inventoryType = inventoryType;
    }

    public int size() {
        return this.size;
    }

    public InventoryType type() {
        return this.inventoryType;
    }

    /**
     * Creates an inventory from the {@link GuiType}
     *
     * @param title the title
     * @return a SukettoInventory
     */
    @NMS
    public SukettoInventory create(BaseComponent[] title) {
        if (this.inventoryType == InventoryType.CHEST) {
            return NMSHandle.INVENTORY.inventory(null, this.size, title);
        }

        return NMSHandle.INVENTORY.inventory(null, this.inventoryType, title);
    }
}
