package sh.miles.suketto.nms.v1_20_1.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.network.chat.Component;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;

/**
 * Internal Utility Class
 */
@SuppressWarnings("deprecation")
public final class InternalChatUtils {

    /**
     * Utility Class
     */
    private InternalChatUtils() {

    }

    public static BaseComponent[] toBungeeComponent(Component component) {
        return ComponentSerializer.parse(CraftChatMessage.toJSON(component));
    }

    public static Component toMojangComponent(BaseComponent[] component) {
        return CraftChatMessage.fromJSON(ComponentSerializer.toString(component));
    }

    public static BaseComponent[] toBungeeComponent(String legacy){
        return ComponentSerializer.parse(CraftChatMessage.fromStringToJSON(legacy));
    }

}
