package sh.miles.suketto.nms.v1_19_4.inventory.item;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R3.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_19_R3.util.CraftChatMessage;
import org.bukkit.craftbukkit.v1_19_R3.util.CraftMagicNumbers;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.nms.inventory.item.ComponentItemStackBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class ComponentItemStackBuilderImpl implements ComponentItemStackBuilder {

    private static final Field handleField;

    static {
        try {
            handleField = CraftItemStack.class.getDeclaredField("handle");
            handleField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private CraftItemStack item;
    private Material material;
    private int amount = 1;
    private BaseComponent[] name;
    private List<BaseComponent[]> lore = new ArrayList<>();
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private List<ItemFlag> flags = new ArrayList<>();

    @Nullable
    @Override
    public Material material() {
        return this.material;
    }

    @NotNull
    @Override
    public ComponentItemStackBuilder material(Material material) {
        this.material = material;
        return this;
    }

    @Override
    public int amount() {
        return this.amount;
    }

    @NotNull
    @Override
    public ComponentItemStackBuilder amount(int amount) {
        if (amount <= 0) {
            amount = 1;
        }
        this.amount = amount;
        return this;
    }

    @Nullable
    @Override
    public BaseComponent[] name() {
        return this.name;
    }

    @NotNull
    @Override
    public ComponentItemStackBuilder name(BaseComponent[] name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Override
    public List<BaseComponent[]> lores() {
        return this.lore;
    }

    @NotNull
    @Override
    public ComponentItemStackBuilder lore(BaseComponent[] lore) {
        this.lore.add(lore);
        return this;
    }

    @NotNull
    @Override
    public ComponentItemStackBuilder lores(List<BaseComponent[]> lores) {
        this.lore = new ArrayList<>(lores);
        return this;
    }

    @NotNull
    @Override
    public Map<Enchantment, Integer> enchantments() {
        return this.enchantments;
    }

    @NotNull
    @Override
    public ComponentItemStackBuilder enchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = new HashMap<>(enchantments);
        return this;
    }

    @NotNull
    @Override
    public ComponentItemStackBuilder enchantment(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);
        return this;
    }

    @NotNull
    @Override
    public List<ItemFlag> itemFlags() {
        return this.flags;
    }

    @NotNull
    @Override
    public ComponentItemStackBuilder itemFlags(Collection<ItemFlag> flags) {
        this.flags = new ArrayList<>(flags);
        return this;
    }

    @NotNull
    @Override
    public ComponentItemStackBuilder itemFlag(ItemFlag flag) {
        this.flags.add(flag);
        return this;
    }

    @NotNull
    @Override
    public ItemStack build() throws IllegalStateException {
        Preconditions.checkNotNull(material, "Material must not be null");

        final CraftItemStack craftItem = CraftItemStack.asCraftCopy(new ItemStack(this.material));
        final net.minecraft.world.item.ItemStack item = getHandle(craftItem);
        item.setHoverName(fromBungee(this.name));
        craftItem.setAmount(this.amount);
        this.enchantments.forEach(craftItem::addUnsafeEnchantment);
        final ItemMeta meta = craftItem.getItemMeta();
        meta.addItemFlags(this.itemFlags().toArray(ItemFlag[]::new));

        // lore
        final CompoundTag tag = item.getTag();
        if (!item.hasTag()) {
            return craftItem;
        }

        final CompoundTag display = tag.getCompound(net.minecraft.world.item.ItemStack.TAG_DISPLAY);
        if (tag.contains(net.minecraft.world.item.ItemStack.TAG_DISPLAY)) {
            ListTag listTag = new ListTag();

            for (int i = 0; i < this.lore.size(); i++) {
                listTag.add(i, StringTag.valueOf(toJson(this.lore.get(i))));
            }

            display.put(net.minecraft.world.item.ItemStack.TAG_LORE, listTag);
        }
        // lore end pain :(

        return craftItem;
    }

    @Override
    public ComponentItemStackBuilder load(ItemStack itemStack) {
        if (itemStack instanceof CraftItemStack craftItemStack) {
            this.item = craftItemStack;
        } else {
            this.item = CraftItemStack.asCraftCopy(itemStack);
        }

        final net.minecraft.world.item.ItemStack handle = getHandle(this.item);

        this.name = toBungee(handle.getDisplayName());

        // lore
        final CompoundTag tag = handle.getTag();
        if (tag != null) {
            final CompoundTag display = tag.getCompound(net.minecraft.world.item.ItemStack.TAG_DISPLAY);
            if (tag.contains(net.minecraft.world.item.ItemStack.TAG_DISPLAY)) {
                ListTag loreTag = display.getList(net.minecraft.world.item.ItemStack.TAG_LORE, CraftMagicNumbers.NBT.TAG_LIST);
                List<BaseComponent[]> lore = loreTag.stream()
                        .map((t) -> ComponentSerializer.parse(t.getAsString()))
                        .toList();
                this.lore = new ArrayList<>(lore);
            }
        }
        // end lore
        final ItemMeta meta = item.getItemMeta();

        this.enchantments = new HashMap<>(meta.getEnchants());
        this.flags = new ArrayList<>(meta.getItemFlags());

        return this;
    }

    private static Component fromBungee(BaseComponent[] components) {
        return CraftChatMessage.fromJSON(ComponentSerializer.toString(components));
    }

    private static BaseComponent[] toBungee(Component component) {
        return ComponentSerializer.parse(CraftChatMessage.toJSON(component));
    }

    private static String toJson(BaseComponent[] components) {
        return ComponentSerializer.toString(components);
    }

    private static net.minecraft.world.item.ItemStack getHandle(CraftItemStack itemStack) {
        try {
            return (net.minecraft.world.item.ItemStack) handleField.get(itemStack);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
