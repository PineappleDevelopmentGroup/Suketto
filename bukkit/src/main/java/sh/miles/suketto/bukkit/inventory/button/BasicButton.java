package sh.miles.suketto.bukkit.inventory.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * Basic Implementation of the button
 */
public class BasicButton implements Button {

    private final ItemStack item;
    private final Consumer<InventoryClickEvent> click;

    public BasicButton(@NotNull final ItemStack item, @NotNull final Consumer<InventoryClickEvent> click) {
        this.item = item;
        this.click = click;
    }

    @Override
    public ItemStack icon() {
        return this.item;
    }

    @Override
    public void click(@NotNull InventoryClickEvent event) {
        this.click.accept(event);
    }
}
