package sh.miles.suketto.bukkit.inventory.gui.impl;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.inventory.button.Button;
import sh.miles.suketto.bukkit.inventory.button.PageButton;
import sh.miles.suketto.bukkit.inventory.gui.AbstractGui;
import sh.miles.suketto.bukkit.inventory.gui.GuiType;
import sh.miles.suketto.core.collection.hold.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A Simple Implementation of {@link AbstractGui} which provides similar functionality to {@link SimpleGui} however,
 * provides functionality allowing for multiple pages.
 */
public class SimplePagedGui extends AbstractGui {

    private final List<Page> pages;
    private Page selected;
    private int maxPage;
    private int selectedPage;

    protected SimplePagedGui(@NotNull GuiType type, @NotNull BaseComponent[] title) {
        super(type, title);
        this.pages = new ArrayList<>();
        this.selected = null;
        this.selectedPage = 0;
    }

    /**
     * Retrieves a page from a specific index given it's the next index;
     *
     * @param index the page index
     * @return the current page if it exists otherwise a new page
     */
    public Page page(final int index) {
        try {
            return this.pages.get(index);
        } catch (IndexOutOfBoundsException e) {
            final Page page = new Page();
            this.pages.add(page);
            this.maxPage += 1;
            return page;
        }
    }

    /**
     * Inserts a page into the list if it is not yet in the list. This method should NOT be used with a page created by
     * {@link #page(int)} which automatically appends the created page to the List
     *
     * @param page the page
     */
    public void insert(@NotNull final Page page) {
        if (this.pages.contains(page)) {
            return;
        }

        this.pages.add(page);
        this.maxPage += 1;
    }

    /**
     * Flips to the next page
     *
     * @param player the player
     * @throws IndexOutOfBoundsException if there is no next page
     */
    public void next(@NotNull final Player player) throws IndexOutOfBoundsException {
        if (this.selectedPage + 1 >= maxPage) {
            throw new IndexOutOfBoundsException("This SimplePagedGui next has exceeded its maximum value");
        }

        this.selectedPage += 1;
        decorate(player);
    }

    /**
     * Flips to the previous page
     *
     * @param player the player
     * @throws IndexOutOfBoundsException if there is no previous page
     */
    public void previous(@NotNull final Player player) throws IndexOutOfBoundsException {
        if (this.selectedPage - 1 < 0) {
            throw new IndexOutOfBoundsException("This SimplePagedGui previous has no previous pages");
        }

        this.selectedPage -= 1;
        decorate(player);
    }

    /**
     * Checks whether there is a next page
     *
     * @return true if there is a next page
     */
    public boolean hasNext() {
        return this.selectedPage + 1 < maxPage;
    }

    /**
     * Checks whether there is a previous page
     *
     * @return true if there is a previous page
     */
    public boolean hasPrevious() {
        return this.selectedPage > 0;
    }


    /**
     * Sets the starting page
     *
     * @param index the page index to start on
     */
    public void startingPage(final int index) {
        if (index >= pages.size()) {
            throw new IndexOutOfBoundsException("The starting page exceeds the number of pages");
        }
        this.selectedPage = index;
    }

    @Override
    public void handleClick(@NotNull InventoryClickEvent event) {
        final Button button = this.selected.buttons.get(event.getSlot());
        if (button != null) {
            button.click(event);
        }
    }

    @Override
    protected void decorate(@NotNull Player player) {
        this.selected = this.pages.get(this.selectedPage);
        selected.contents.forEach(super.inventory::setItem);
        selected.buttons.forEach((Integer slot, Button button) -> super.inventory.setItem(slot, button.icon()));
    }

    /**
     * Represents a page within a {@link SimplePagedGui}
     */
    static class Page {

        private final Map<Integer, ItemStack> contents;
        private final Map<Integer, Button> buttons;

        public Page() {
            this.contents = new HashMap<>();
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
         * Inserts a non-interactive item into the contents map
         *
         * @param item  the item
         * @param slots the slots
         */
        public void insert(@NotNull final ItemStack item, int... slots) {
            for (int slot : slots) {
                this.contents.put(slot, item);
            }
        }

        /**
         * Inserts a {@link PageButton} which is capable of two options {@link PageButton.Action#NEXT} and
         * {@link PageButton.Action#PREVIOUS}
         *
         * @param button the PageButton to insert
         * @param slot   the slot to put the button in
         */
        public void insertPageButton(@NotNull final PageButton button, final int slot) {
            this.buttons.put(slot, button);
        }

        /**
         * Provides a copy of the contents of this page
         *
         * @return a map of Integer key, ItemStack value
         */
        public Map<Integer, ItemStack> contents() {
            return new HashMap<>(this.contents);
        }

        /**
         * Provides a copy of the buttons of this page
         *
         * @return a map of Integer key, Button value
         */
        public Map<Integer, Button> buttons() {
            return new HashMap<>(this.buttons);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Page page)) return false;
            return Objects.equals(contents, page.contents) && Objects.equals(buttons, page.buttons);
        }

        @Override
        public int hashCode() {
            return Objects.hash(contents, buttons);
        }
    }
}
