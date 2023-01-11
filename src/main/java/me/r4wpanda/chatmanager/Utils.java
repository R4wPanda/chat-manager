package me.r4wpanda.chatmanager;

import java.util.HashMap;

public class Utils {

    private static HashMap<String, String> replacementMap = new HashMap<>();

    private static CensoredConfigManager CCManager;

    public Utils(CensoredConfigManager CCManager) {
        Utils.CCManager = CCManager;
    }

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
}
