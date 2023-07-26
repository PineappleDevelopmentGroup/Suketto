package sh.miles.suketto.bukkit.minecraft.nms;

import sh.miles.suketto.bukkit.internal.annotations.NMS;
import sh.miles.suketto.bukkit.minecraft.MinecraftVersion;
import sh.miles.suketto.core.utils.ReflectionUtils;

@NMS
public enum NMSHandleType {

    ITEM_HANDLE(),
    INVENTORY_HANDLE(),
    ;

    private static final String PATH = "sh.miles.suketto.nms.%s%s%s";
    private static final String SUFFIX = "Impl";

    private static <T> T getHandle(Class<T> clazz) {
        return ReflectionUtils.newInstance(PATH.formatted(clazz.getSimpleName()), new Object[0]);
    }

    private static class Handle<T> {


        private final MinecraftVersion version;
        private final Class<T> clazz;

        [

        Handle(MinecraftVersion version, Class<T> clazz) {
            this.version = version;
            this.clazz = clazz;
        }]

    }

}
