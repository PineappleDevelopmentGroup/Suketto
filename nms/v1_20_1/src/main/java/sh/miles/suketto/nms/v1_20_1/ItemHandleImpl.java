package sh.miles.suketto.nms.v1_19_4;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import sh.miles.suketto.nms.ItemHandle;
import sh.miles.suketto.nms.inventory.item.ComponentItemStackBuilder;
import sh.miles.suketto.nms.v1_19_4.inventory.item.ComponentItemStackBuilderImpl;

import java.util.List;

public class ItemHandleImpl implements ItemHandle {

    @Override
    public ComponentItemStackBuilder item(Material material) {
        return new ComponentItemStackBuilderImpl().material(material);
    }

    @Override
    public ComponentItemStackBuilder item(Material material, BaseComponent[] name) {
        return new ComponentItemStackBuilderImpl().material(material).name(name);
    }

    @Override
    public ComponentItemStackBuilder item(Material material, BaseComponent[] name, List<BaseComponent[]> lore) {
        return new ComponentItemStackBuilderImpl().material(material).name(name).lores(lore);
    }

    @Override
    public ComponentItemStackBuilder item(ItemStack itemStack) {
        return new ComponentItemStackBuilderImpl().load(itemStack);
    }
}
