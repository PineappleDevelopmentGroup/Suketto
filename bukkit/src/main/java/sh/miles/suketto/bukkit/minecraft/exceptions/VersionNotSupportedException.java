package sh.miles.suketto.bukkit.minecraft.exceptions;

import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.minecraft.MinecraftVersion;

public class VersionNotSupportedException extends Exception {

    public VersionNotSupportedException(@NotNull final MinecraftVersion version, @NotNull final String completePath) {
        super("the package %s  is not yet supported by the version %s check for updates in Suketto before reporting".formatted(completePath, version.getName()));
    }
}
