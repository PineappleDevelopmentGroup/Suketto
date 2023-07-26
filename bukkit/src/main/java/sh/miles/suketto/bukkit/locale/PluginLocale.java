package sh.miles.suketto.bukkit.locale;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Map;

/**
 * Regulates Locales for the plugin
 */
public class PluginLocale {

    private final Map<Locale, TranslationBundle> bundles;
    private TranslationBundle selectedBundle;

    public PluginLocale(@NotNull final Plugin plugin, @NotNull final String bundleDirectory, final String[] locales, final String[] files) {
        this.bundles = TranslationBundle.generateTranslationBundles(plugin, bundleDirectory, locales, files);
    }

    /**
     * Sets the selected bundle
     *
     * @param locale the locale to set
     * @return true if there is a bundle with that locale otherwise false
     */
    public boolean selectedBundle(@NotNull final Locale locale) {
        final TranslationBundle bundle = bundles.get(locale);
        if (bundle == null) {
            return false;
        }

        selectedBundle = bundle;
        return true;
    }

    /**
     * Gets the selected bundle if there is one otherwise null
     *
     * @return the selected bundle
     */
    @Nullable
    public TranslationBundle selectedBundle() {
        return this.selectedBundle;
    }

}
