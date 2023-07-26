package sh.miles.suketto.bukkit.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Equivalent to {@link TabCompleter}
 */
@FunctionalInterface
public interface CommandCompleter {

    List<String> complete(@NotNull final CommandSender sender, @NotNull final String[] args);

}
