package sh.miles.suketto.nms;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import sh.miles.suketto.nms.inventory.item.ComponentItemStackBuilder;

import java.util.List;

/**
 * Handles Item Interactions with NMS
 */
public interface ItemHandle {

    /**
     * Creates a builder from the given material
     *
     * @param material the material
     * @return a builder from the Material
     */
    ComponentItemStackBuilder item(Material material);

    /**
     * Creates a builder from the given material and name
     *
     * @param material the material
     * @param name     the name of the item
     * @return a builder from the Material and Name
     */
    ComponentItemStackBuilder item(Material material, BaseComponent[] name);

    /**
     * Creates a builder from the given material, name and lore
     *
     * @param material the material
     * @param name     the name
     * @param lore     the lore
     * @return a builder from the Material, Name and Lore
     */
    ComponentItemStackBuilder item(Material material, BaseComponent[] name, List<BaseComponent[]> lore);

    /**
     * Creates a builder from the given ItemSTack
     *
     * @param itemStack the item stack
     * @return a builder from the ItemStack
     */
    ComponentItemStackBuilder item(ItemStack itemStack);


}
