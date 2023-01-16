package me.r4wpanda.chatmanager;

import java.util.HashMap;
import java.util.Set;

public class Utils {

    private static final HashMap<String, String> REPLACEMENT_MAP = new HashMap<>();

    private static CensoredConfigManager censorManager;

    public static void registerManager(CensoredConfigManager censorManager) {
        Utils.censorManager = censorManager;
    }

    public static String sanitizeString(String word) {
        Set<String> replacedCharsKeys = censorManager.getCensoredConfig().getConfigurationSection("CensoredWords.replace-chars").getKeys(false);

        for (String key : replacedCharsKeys) {
            REPLACEMENT_MAP.put(key, censorManager.getCensoredConfig().getString("CensoredWords.replace-chars." + key));
        }

        String str = word;

        for (String key : replacedCharsKeys) {
            if (word.contains(key)) {
                str = str.replace(key, REPLACEMENT_MAP.get((key)));
            }
        }
        return str;
    }
}