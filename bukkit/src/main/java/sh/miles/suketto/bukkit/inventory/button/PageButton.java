package sh.miles.suketto.bukkit.inventory.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.inventory.gui.impl.SimplePagedGui;

import java.util.function.Consumer;

/**
 * Page Button functions has a helper for {@link SimplePagedGui} and provides basic preset actions for the GUI. This
 * Button can be easily reimplemented using {@link SimplePagedGui#hasNext()}, {@link SimplePagedGui#hasPrevious()} and
 * their pairings, {@link SimplePagedGui#next(Player)}, {@link SimplePagedGui#previous(Player)}. for a more simple
 * implementation.
 */
public class PageButton implements Button {

    private final ItemStack icon;
    private final Consumer<InventoryClickEvent> success;
    private final Consumer<InventoryClickEvent> fail;
    private final Action action;
    private final SimplePagedGui gui;

    public PageButton(@NotNull final ItemStack item, @NotNull final Consumer<InventoryClickEvent> success, @NotNull final Consumer<InventoryClickEvent> fail, @NotNull final Action action, @NotNull final SimplePagedGui gui) {
        this.icon = icon();
        this.success = success;
        this.fail = fail;
        this.action = action;
        this.gui = gui;
    }

    public PageButton(@NotNull final ItemStack item, @NotNull final Action action, @NotNull final SimplePagedGui gui) {
        this(item, (e) -> {
        }, (e) -> {
        }, action, gui);
    }

    @Override
    public ItemStack icon() {
        return this.icon;
    }

    @Override
    public void click(@NotNull InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        boolean success = false;
        switch (action) {
            case NEXT -> {
                if (this.gui.hasNext()) {
                    this.gui.next(player);
                    success = true;
                }
            }
            case PREVIOUS -> {
                if (this.gui.hasPrevious()) {
                    this.gui.previous(player);
                    success = true;
                }
            }
        }

        if (success) {
            this.success.accept(event);
        } else {
            this.fail.accept(event);
        }
    }

    public enum Action {
        NEXT,
        PREVIOUS,
    }
}
