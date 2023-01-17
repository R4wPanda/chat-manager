package me.r4wpanda.chatmanager.utils;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public class Utils {

    private static final HashMap<String, String> replacementMap = new HashMap<>();

    public static String desanitizeString(String word) {

        for (String key : plugin.getConfigHandler().getConfig("blacklistedWords").getConfigurationSection("BlacklistedWords.replace-chars").getKeys(false)) {
            replacementMap.put(key, plugin.getConfigHandler().getConfig("blacklistedWords").getString("BlacklistedWords.replace-chars." + key));
        }

        String str = word;

        for (String key : plugin.getConfigHandler().getConfig("blacklistedWords").getConfigurationSection("BlacklistedWords.replace-chars.").getKeys(false)) {
            if (word.contains(key)) {
                str = str.replace(key, replacementMap.get((key)));
            }
        }

        return str;
    }

    private final static ChatManagerPlugin plugin = JavaPlugin.getPlugin(ChatManagerPlugin.class);

    public static boolean checkFeatureToggle(String featureKey) {

        File file = new File(plugin.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        boolean featureToggle = config.getBoolean("features." + featureKey);
        return featureToggle;

    }

}
