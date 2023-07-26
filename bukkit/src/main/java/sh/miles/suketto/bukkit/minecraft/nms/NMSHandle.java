package sh.miles.suketto.bukkit.minecraft.nms;

import sh.miles.suketto.bukkit.internal.annotations.NMS;
import sh.miles.suketto.bukkit.minecraft.MinecraftVersion;
import sh.miles.suketto.core.utils.ReflectionUtils;
import sh.miles.suketto.nms.InventoryHandle;
import sh.miles.suketto.nms.ItemHandle;

/**
 * Provides sources for all NMS Handler classes provided by sh.miles.suketto.nms from the base module
 */
@NMS
public final class NMSHandle {

    private static final String PATH = "sh.miles.suketto.nms.%s.%s%s";
    private static final String SUFFIX = "Impl";

    public static final ItemHandle ITEM = NMSHandle.getHandle(ItemHandle.class);
    public static final InventoryHandle INVENTORY = NMSHandle.getHandle(InventoryHandle.class);

    /**
     * Utility
     */
    private NMSHandle() {

    }

    private static <T> T getHandle(Class<T> clazz) {
        return ReflectionUtils.newInstance(
                PATH.formatted(MinecraftVersion.CURRENT.getInternalName(), clazz.getSimpleName(), SUFFIX),
                new Object[0]
        );
    }

}
