package sh.miles.suketto.bukkit.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.core.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Bridges the gap between spigot and Suketto command interfaces and interactions
 */
public final class SpigotCommandBridge {

    private static CommandMap commandMap;
    private static Map<String, org.bukkit.command.Command> knownCommands;


    /**
     * Utility
     */
    private SpigotCommandBridge() {
    }

    /**
     * Registers a command to the server by using spigot's internal {@link PluginCommand} class
     *
     * @param command the command to register
     */
    public static void register(@NotNull final Plugin plugin, @NotNull final Command command) {
        final CommandLabel label = command.commandLabel();
        final PluginCommand pluginCommand = ReflectionUtils.newInstance(PluginCommand.class, new Object[]{label.name(), plugin});
        if (pluginCommand == null) {
            throw new RuntimeException("Creation of PluginCommand failed");
        }
        pluginCommand.setName(label.name());
        pluginCommand.setAliases(label.aliases());
        pluginCommand.setPermission(label.permission());
        pluginCommand.setUsage("/" + label.name());
        pluginCommand.setExecutor((s, c, l, a) -> command.execute(s, a));
        pluginCommand.setTabCompleter((s, c, l, a) -> command.complete(s, a));

        if (commandMap == null) {
            init();
        }

        if (!commandMap.register(plugin.getName(), pluginCommand)) {
            throw new IllegalStateException("Command with the name " + pluginCommand.getName() + " already exists");
        }
    }

    /**
     * Unregisters all commands associated with the provided plugin
     *
     * @param plugin the plugins whose commands to unregister
     */
    public static void unregisterAll(@NotNull final Plugin plugin) {
        if (knownCommands == null) {
            init();
        }

        knownCommands.entrySet().removeIf((Map.Entry<String, org.bukkit.command.Command> entry) -> {
            final String label = entry.getKey();
            if (!(entry.getValue() instanceof PluginCommand pluginCommand)) {
                return false;
            }

            return plugin.getName().equals(pluginCommand.getPlugin().getName());
        });
    }

    @SuppressWarnings("unchecked")
    private static void init() {
        commandMap = ReflectionUtils.getField(Bukkit.getPluginManager(), "commandMap", CommandMap.class);
        knownCommands = (Map<String, org.bukkit.command.Command>) ReflectionUtils.getField(commandMap, "knownCommands", Map.class);
    }

}
