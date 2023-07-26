package sh.miles.suketto.bukkit.inventory.gui.impl;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.inventory.button.Button;
import sh.miles.suketto.bukkit.inventory.gui.AbstractGui;
import sh.miles.suketto.bukkit.inventory.gui.GuiType;

import java.util.HashMap;
import java.util.Map;

/**
 * A Simple Implementation of {@link AbstractGui} which provides basic GUI functionality This class is intended to be
 * extended, but you can use it without doing so.
 */
public class SimpleGui extends AbstractGui {

    private final Map<Integer, Button> buttons;

    protected SimpleGui(@NotNull GuiType type, @NotNull BaseComponent[] title) {
        super(type, title);
        this.buttons = new HashMap<>();
    }

    /**
     * Inserts a button into the provided slots
     *
     * @param button the button to insert
     * @param slots  the slots
     */
    public void insert(@NotNull final Button button, int... slots) {
        for (int slot : slots) {
            this.buttons.put(slot, button);
        }
    }

    /**
     * Inserts an item directly into the {@link Inventory}, note, all items inserted using methods will not be
     * functional as clickable items unless you override {@link #handleClick(InventoryClickEvent)} which is implemented
     * by this class. If you want clickable items to have functions it's recommended to use.
     * {@link #insert(Button, int...)} instead
     *
     * @param item  the item to insert
     * @param slots the slots
     */
    public void insert(@NotNull final ItemStack item, int... slots) {
        for (int slot : slots) {
            super.inventory.setItem(slot, item);
        }
    }

    @Override
    public void handleClick(@NotNull InventoryClickEvent event) {
        final Button button = this.buttons.get(event.getSlot());
        if (button != null) {
            button.click(event);
        }
    }

    @Override
    protected void decorate(@NotNull Player player) {
        this.buttons.forEach((Integer slot, Button button) -> super.inventory.setItem(slot, button.icon()));
    }
}
