package sh.miles.suketto.bukkit.inventory.gui;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.inventory.InventoryEventHandler;

/**
 * An interface to be implemented for basic GUI design
 */
public abstract class Gui implements InventoryEventHandler {


    

    protected abstract void decorate(@NotNull final Player player);

}
