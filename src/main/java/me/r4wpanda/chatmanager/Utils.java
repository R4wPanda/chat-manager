package me.r4wpanda.chatmanager;

import java.util.HashMap;
import java.util.Set;

public class Utils {

    private static HashMap<String, String> replacementMap = new HashMap<>();

    private static CensoredConfigManager CCManager;

    public static void registerManager(CensoredConfigManager censorManager) {
        CCManager = censorManager;
    }

    public static String sanitizeString(String word) {
        Set<String> replacedCharsKeys = CCManager.getCensoredConfig().getConfigurationSection("CensoredWords.replace-chars").getKeys(false);

        for (String key : replacedCharsKeys) {
            replacementMap.put(key, CCManager.getCensoredConfig().getString("CensoredWords.replace-chars." + key));
        }

        String str = word;

        for (String key : replacedCharsKeys) {
            if (word.contains(key)) {
                str = str.replace(key, replacementMap.get((key)));
            }
        }
        return str;
    }
}