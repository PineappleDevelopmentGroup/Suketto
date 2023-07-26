package sh.miles.suketto.nms.v1_20_1;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.InventoryHandle;
import sh.miles.suketto.nms.inventory.SukettoInventory;
import sh.miles.suketto.nms.v1_20_1.inventory.SukettoInventoryImpl;

@SuppressWarnings("deprecation")
public class InventoryHandleImpl implements InventoryHandle {

    @Override
    public SukettoInventory inventory(@NotNull InventoryHolder holder, int size, @NotNull BaseComponent[] title) {
        return new SukettoInventoryImpl(holder, size, title);
    }

    @Override
    public SukettoInventory inventory(@NotNull InventoryHolder holder, @NotNull InventoryType inventoryType, @NotNull BaseComponent[] title) {
        return new SukettoInventoryImpl(holder, inventoryType, title);
    }
}
