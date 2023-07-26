package sh.miles.suketto.nms.v1_20_1.inventory;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftContainer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.nms.inventory.SukettoInventory;
import sh.miles.suketto.nms.v1_20_1.utils.InternalChatUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("deprecation")
public class SukettoInventoryImpl implements SukettoInventory {

    private final Inventory inventory;
    private BaseComponent[] title;

    public SukettoInventoryImpl(@NotNull final InventoryHolder holder, final int size, BaseComponent[] title) {
        this.title = title;
        this.inventory = new CraftInventory(new SukettoMinecraftInventory(holder, size, title));
    }

    public SukettoInventoryImpl(@NotNull final InventoryHolder holder, InventoryType type, BaseComponent[] title) {
        this.title = title;
        this.inventory = new CraftInventory(new SukettoMinecraftInventory(holder, type, title));
    }

    @Nullable
    @Override
    public InventoryView open(@NotNull HumanEntity entity) {
        final ServerPlayer player = (ServerPlayer) ((CraftHumanEntity) entity).getHandle();
        MenuType<?> menuType = CraftContainer.getNotchInventoryType(inventory);

        Preconditions.checkArgument(menuType != null, "The menu type could not be found");
        AbstractContainerMenu menu = new CraftContainer(inventory, player, player.nextContainerCounter());

        menu = CraftEventFactory.callInventoryOpenEvent(player, menu);
        if (menu == null) {
            return null;
        }

        player.connection.send(new ClientboundOpenScreenPacket(menu.containerId, menuType, InternalChatUtils.toMojangComponent(this.title)));
        player.containerMenu = menu;
        player.initMenu(menu);
        return player.containerMenu.getBukkitView();
    }

    @Override
    public void setTitle(BaseComponent[] title) {
        this.title = title;
        for (HumanEntity viewer : getInventory().getViewers()) {
            sendInventoryTitleChange(viewer.getOpenInventory(), title);
        }
    }

    @NotNull
    @Override
    public BaseComponent[] getTitle() {
        return this.title;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    static class SukettoMinecraftInventory implements Container {

        private final NonNullList<net.minecraft.world.item.ItemStack> items;
        private int maxStack = MAX_STACK;
        private final List<HumanEntity> viewers;
        private final BaseComponent[] title;
        private InventoryType type;
        private final InventoryHolder owner;

        public SukettoMinecraftInventory(InventoryHolder owner, InventoryType type) {
            this(owner, type.getDefaultSize(), InternalChatUtils.toBungeeComponent(type.getDefaultTitle()));
            this.type = type;
        }

        public SukettoMinecraftInventory(InventoryHolder owner, InventoryType type, BaseComponent[] title) {
            this(owner, type.getDefaultSize(), title);
            this.type = type;
        }

        public SukettoMinecraftInventory(InventoryHolder owner, int size) {
            this(owner, size, InternalChatUtils.toBungeeComponent("Chest"));
        }

        public SukettoMinecraftInventory(InventoryHolder owner, int size, BaseComponent[] title) {
            Preconditions.checkArgument(title != null, "title cannot be null");
            this.items = NonNullList.withSize(size, net.minecraft.world.item.ItemStack.EMPTY);
            this.title = title;
            this.viewers = new ArrayList<HumanEntity>();
            this.owner = owner;
            this.type = InventoryType.CHEST;
        }

        @Override
        public int getContainerSize() {
            return items.size();
        }

        @NotNull
        @Override
        public net.minecraft.world.item.ItemStack getItem(int slot) {
            return items.get(slot);
        }

        @NotNull
        @Override
        public net.minecraft.world.item.ItemStack removeItem(int slot, int amount) {
            net.minecraft.world.item.ItemStack stack = this.getItem(slot);
            net.minecraft.world.item.ItemStack result;
            if (stack == net.minecraft.world.item.ItemStack.EMPTY) return stack;
            if (stack.getCount() <= amount) {
                this.setItem(slot, net.minecraft.world.item.ItemStack.EMPTY);
                result = stack;
            } else {
                result = CraftItemStack.copyNMSStack(stack, amount);
                stack.shrink(amount);
            }
            this.setChanged();
            return result;
        }

        @NotNull
        @Override
        public net.minecraft.world.item.ItemStack removeItemNoUpdate(int slot) {
            net.minecraft.world.item.ItemStack stack = this.getItem(slot);
            net.minecraft.world.item.ItemStack result;
            if (stack == net.minecraft.world.item.ItemStack.EMPTY) return stack;
            if (stack.getCount() <= 1) {
                this.setItem(slot, null);
                result = stack;
            } else {
                result = CraftItemStack.copyNMSStack(stack, 1);
                stack.shrink(1);
            }
            return result;
        }

        @Override
        public void setItem(int slot, net.minecraft.world.item.ItemStack stack) {
            items.set(slot, stack);
            if (stack != net.minecraft.world.item.ItemStack.EMPTY && this.getMaxStackSize() > 0 && stack.getCount() > this.getMaxStackSize()) {
                stack.setCount(this.getMaxStackSize());
            }
        }

        @Override
        public int getMaxStackSize() {
            return maxStack;
        }

        @Override
        public void setMaxStackSize(int size) {
            this.maxStack = size;
        }

        @Override
        public void setChanged() {
        }

        @Override
        public boolean stillValid(@NotNull Player player) {
            return true;
        }

        @NotNull
        @Override
        public List<net.minecraft.world.item.ItemStack> getContents() {
            return items;
        }

        @Override
        public void onOpen(@NotNull CraftHumanEntity who) {
            viewers.add(who);
        }

        @Override
        public void onClose(@NotNull CraftHumanEntity who) {
            viewers.remove(who);
        }

        @NotNull
        @Override
        public List<HumanEntity> getViewers() {
            return viewers;
        }

        public InventoryType getType() {
            return type;
        }

        @NotNull
        @Override
        public InventoryHolder getOwner() {
            return owner;
        }

        @Override
        public boolean canPlaceItem(int slot, @NotNull net.minecraft.world.item.ItemStack stack) {
            return true;
        }

        @Override
        public void startOpen(@NotNull Player player) {

        }

        @Override
        public void stopOpen(@NotNull Player player) {

        }

        @Override
        public void clearContent() {
            items.clear();
        }

        @Override
        public Location getLocation() {
            return null;
        }

        public BaseComponent[] getTitle() {
            return title;
        }

        @Override
        public boolean isEmpty() {
            Iterator iterator = this.items.iterator();

            net.minecraft.world.item.ItemStack itemstack;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                itemstack = (net.minecraft.world.item.ItemStack) iterator.next();
            } while (itemstack.isEmpty());

            return false;
        }
    }

    public static void sendInventoryTitleChange(InventoryView view, BaseComponent[] title) {
        Preconditions.checkArgument(view != null, "InventoryView cannot be null");
        Preconditions.checkArgument(title != null, "Title cannot be null");
        Preconditions.checkArgument(view.getPlayer() instanceof org.bukkit.entity.Player, "NPCs are not currently supported for this function");
        Preconditions.checkArgument(view.getTopInventory().getType().isCreatable(), "Only creatable inventories can have their title changed");

        final ServerPlayer entityPlayer = (ServerPlayer) ((CraftHumanEntity) view.getPlayer()).getHandle();
        final int containerId = entityPlayer.containerMenu.containerId;
        final MenuType<?> windowType = CraftContainer.getNotchInventoryType(view.getTopInventory());
        entityPlayer.connection.send(new ClientboundOpenScreenPacket(containerId, windowType, InternalChatUtils.toMojangComponent(title)));
        ((org.bukkit.entity.Player) view.getPlayer()).updateInventory();
    }
}
