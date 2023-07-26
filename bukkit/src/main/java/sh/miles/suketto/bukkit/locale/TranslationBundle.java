package sh.miles.suketto.bukkit.locale;

import com.google.common.base.Preconditions;
import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.bukkit.chat.Replacement;
import sh.miles.suketto.bukkit.utils.ConfigUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Bundle of files and nested directories for translations
 */
public final class TranslationBundle {

    private final Map<String, String> rawTranslations;

    private TranslationBundle(final Plugin plugin, final String path, final String[] files) {
        this.rawTranslations = initTranslations(plugin, path, files);
    }

    @NotNull
    @Contract("_, null -> new ; _, _ -> new")
    public BaseComponent[] get(@NotNull final String key, @Nullable final Replacement... replacements) {
        Preconditions.checkNotNull(key);
        String rawTranslation = rawTranslations.get(key);
        Preconditions.checkArgument(rawTranslation != null, "Translation for key " + key + " does not exist");

        if (replacements != null) {
            for (Replacement replacement : replacements) {
                rawTranslation = replacement.replace(rawTranslation);
            }
        }

        return MineDown.parse(rawTranslation);
    }

    /**
     * Creates and returns a map with all raw translations
     *
     * @param path  the path to the bundle
     * @param files all files in the bundle
     * @return a map of raw translations
     */
    private static Map<String, String> initTranslations(final Plugin plugin, final String path, final String[] files) {
        final Map<String, String> rawTranslations = new HashMap<>();

        final Map<String, FileConfiguration> bundledConfigs = ConfigUtils.createConfigFiles(plugin, path, files);
        bundledConfigs.forEach((String __, FileConfiguration file) -> {
            for (String key : file.getKeys(false)) {
                rawTranslations.put(key, file.getString(key));
            }
        });

        return rawTranslations;
    }

    static Map<Locale, TranslationBundle> generateTranslationBundles(final Plugin plugin, final String parentDir, final String[] locales, final String[] commonFiles) {
        final Map<Locale, TranslationBundle> bundles = new HashMap<>();
        for (String stringyLocale : locales) {
            final Locale locale = new Locale(stringyLocale);
            final String path = parentDir + File.separator + stringyLocale;
            bundles.put(locale, new TranslationBundle(plugin, path, commonFiles));
        }
        return bundles;
    }
}
