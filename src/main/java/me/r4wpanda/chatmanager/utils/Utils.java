package me.r4wpanda.chatmanager.utils;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import me.r4wpanda.chatmanager.managers.CensoredConfigManager;

import java.util.HashMap;

public class Utils {

    private static ChatManagerPlugin main = ChatManagerPlugin.getInstance();

    private final static HashMap<String, String> replacementMap = new HashMap<>();

    private final static CensoredConfigManager CCManager = CensoredConfigManager.getInstance();

    public static String sanitizeString(String word) {

        for (String key : CCManager.getCensoredConfig().getConfigurationSection("CensoredWords.replace-chars").getKeys(false)) {
            replacementMap.put(key, CCManager.getCensoredConfig().getString("CensoredWords.replace-chars." + key));
        }

        String str = word;

        for (String key : CCManager.getCensoredConfig().getConfigurationSection("CensoredWords.replace-chars.").getKeys(false)) {
            if (word.contains(key)) {
                str = str.replace(key, replacementMap.get((key)));
            }
        }
        return str;

    }

    public static void mkDefDir() {
        if (!main.getDataFolder().exists()) {
            main.getDataFolder().mkdir();
        }
    }
}
