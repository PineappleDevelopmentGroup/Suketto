package sh.miles.suketto.bukkit.inventory.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.inventory.gui.impl.SimplePagedGui;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Button related creation methods
 */
public final class ButtonFactory {

    @NotNull
    public static Function<Player, BasicButton> makeBasicButton(@NotNull final Function<Player, ItemStack> icon, @NotNull final Consumer<InventoryClickEvent> click) {
        return (Player player) -> new BasicButton(icon.apply(player), click);
    }

    @NotNull
    public static BasicButton makeBasicButton(@NotNull final ItemStack icon, @NotNull final Consumer<InventoryClickEvent> click) {
        return new BasicButton(icon, click);
    }

    @NotNull
    public static Function<Player, PageButton> makePageButton(@NotNull final Function<Player, ItemStack> icon, @NotNull final SimplePagedGui gui, @NotNull final Consumer<InventoryClickEvent> success, @NotNull final Consumer<InventoryClickEvent> failure, @NotNull final PageButton.Action action) {
        return (Player player) -> new PageButton(icon.apply(player), success, failure, action, gui);
    }

    @NotNull
    public static Function<Player, PageButton> makePageButton(@NotNull final Function<Player, ItemStack> icon, @NotNull final PageButton.Action action, @NotNull final SimplePagedGui gui) {
        return (Player player) -> new PageButton(icon.apply(player), action, gui);
    }

    @NotNull
    public static PageButton makePageButton(@NotNull final ItemStack icon, @NotNull final Consumer<InventoryClickEvent> success, @NotNull final Consumer<InventoryClickEvent> failure, @NotNull final PageButton.Action action, @NotNull final SimplePagedGui gui) {
        return new PageButton(icon, success, failure, action, gui);
    }

    @NotNull
    public static PageButton makePageButton(@NotNull final ItemStack icon, @NotNull final PageButton.Action action, @NotNull final SimplePagedGui gui){
        return new PageButton(icon, action, gui);
    }

}
