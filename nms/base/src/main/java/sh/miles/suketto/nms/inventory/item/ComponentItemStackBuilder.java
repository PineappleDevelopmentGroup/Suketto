package sh.miles.suketto.nms.inventory.item;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Utilizes net.minecraft.world.item.ItemStack and allows to put components on all ItemStack related sources
 */
public interface ComponentItemStackBuilder {


    /**
     * Gets the material
     *
     * @return the material or null
     */
    @Nullable
    Material material();

    /**
     * Sets the material
     *
     * @param material the material to set
     * @return this class
     */
    @NotNull
    ComponentItemStackBuilder material(Material material);

    /**
     * Gets the amount of item
     *
     * @return the amount of the item
     */
    int amount();

    /**
     * Sets the amount of the item
     *
     * @param amount the amount
     * @return this class
     */
    @NotNull
    ComponentItemStackBuilder amount(final int amount);

    /**
     * Gets the name if any
     *
     * @return the name or null
     */
    @Nullable
    BaseComponent[] name();

    /**
     * Sets the name
     *
     * @param name the name to set
     * @return this class
     */
    @NotNull
    ComponentItemStackBuilder name(BaseComponent[] name);

    /**
     * A List of all lore
     *
     * @return a list of lore
     */
    @NotNull
    List<BaseComponent[]> lores();

    /**
     * sets a lore line
     *
     * @param lore the lore line to set
     * @return this class
     */
    @NotNull
    ComponentItemStackBuilder lore(BaseComponent[] lore);

    /**
     * sets the whole lore of the item
     *
     * @param lores the lore lines
     * @return this class
     */
    @NotNull
    ComponentItemStackBuilder lores(List<BaseComponent[]> lores);

    /**
     * gets a map of all enchantments on the item
     *
     * @return a map of enchantments and levels
     */
    @NotNull
    Map<Enchantment, Integer> enchantments();

    /**
     * Sets all enchantments in the enchantment map
     *
     * @param enchantments all enchantments
     * @return this class
     */
    @NotNull
    ComponentItemStackBuilder enchantments(Map<Enchantment, Integer> enchantments);

    /**
     * Adds an enchantment to the enchantment map
     *
     * @param enchantment the enchantment to add
     * @param level       the level of the enchantment
     * @return this class
     */
    @NotNull
    ComponentItemStackBuilder enchantment(Enchantment enchantment, int level);

    /**
     * Gets a list of all ItemFlags on the item
     *
     * @return a list of itemFlags
     */
    @NotNull
    List<ItemFlag> itemFlags();

    /**
     * Sets a list of all ItemFlags on the item stacks
     *
     * @param flags a list of all item flags to set
     * @return this class
     */
    @NotNull
    ComponentItemStackBuilder itemFlags(Collection<ItemFlag> flags);

    /**
     * Adds an ItemFlag on the ItemStack
     *
     * @param flag the flag to add
     * @return this class
     */
    @NotNull
    ComponentItemStackBuilder itemFlag(ItemFlag flag);

    /**
     * Builds the ItemStack
     *
     * @return an ItemStack
     * @throws IllegalStateException if the stack fails to create
     */
    @NotNull
    ItemStack build() throws IllegalStateException;

    /**
     * Loads the provided ItemStacks data into the builder
     *
     * @param itemStack the ItemStack to load
     */
    ComponentItemStackBuilder load(ItemStack itemStack);

}
