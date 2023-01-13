package me.r4wpanda.chatmanager.utils;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigUtils {

    private static ChatManagerPlugin main = ChatManagerPlugin.getInstance();

    public static boolean checkFeatureToggle(String featureKey) {
        File file = new File(main.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        boolean featureToggle = config.getBoolean("features." + featureKey);
        return featureToggle;
    }


}
