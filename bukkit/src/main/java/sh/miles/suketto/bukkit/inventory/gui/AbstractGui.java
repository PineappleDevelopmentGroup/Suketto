package sh.miles.suketto.bukkit.inventory.gui;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.internal.annotations.NMS;
import sh.miles.suketto.bukkit.inventory.InventoryEventHandler;
import sh.miles.suketto.nms.inventory.SukettoInventory;

/**
 * An interface to be implemented for basic GUI design
 */
@NMS
public abstract class AbstractGui implements InventoryEventHandler {


    protected final SukettoInventory sukettoInventory;
    protected final Inventory inventory;

    protected AbstractGui(@NotNull final GuiType type, @NotNull final BaseComponent[] title) {
        this.sukettoInventory = type.create(title);
        this.inventory = sukettoInventory.getInventory();
    }

    /**
     * Opens the Inventory
     *
     * @param player the player to open the inventory
     */
    public void open(@NotNull final Player player) {
        sukettoInventory.open(player);
    }

    /**
     * Provides a raw {@link SukettoInventory}
     *
     * @return a SukettoInventory
     */
    public SukettoInventory getSukettoInventory() {
        return this.sukettoInventory;
    }

    /**
     * Called when a Gui is opened by a player
     *
     * @param player the player who opened the Gui
     */
    protected abstract void decorate(@NotNull final Player player);

    @Override
    public void handleOpen(@NotNull InventoryOpenEvent event) {
        decorate((Player) event.getPlayer());
    }

    @Override
    public void handleClose(@NotNull InventoryCloseEvent event) {
    }


}
