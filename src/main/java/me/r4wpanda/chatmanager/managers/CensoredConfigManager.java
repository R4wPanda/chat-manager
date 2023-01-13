package me.r4wpanda.chatmanager.managers;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import me.r4wpanda.chatmanager.utils.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CensoredConfigManager {

    private ChatManagerPlugin main = ChatManagerPlugin.getInstance();
    private static CensoredConfigManager instance;
    public static CensoredConfigManager getInstance() {return instance;}

    public CensoredConfigManager() {
        instance = this;
        saveCensoredConfig();
    }

    private void saveCensoredConfig() {
        main.mkDefDir();
        File censoredWordsFile = new File(main.getDataFolder(), "censoredWords.yml");
        if (!censoredWordsFile.exists()) {
            main.saveResource("censoredWords.yml", false);
        }
    }

    public FileConfiguration getCensoredConfig() {
        File censoredWordsFile = new File(main.getDataFolder(), "censoredWords.yml");
        return YamlConfiguration.loadConfiguration(censoredWordsFile);
    }

    public List<String> getCensoredWords() {

        List<String> listValues = new ArrayList<>();

        if (getCensoredConfig().getStringList("CensoredWords.censored-word-list").isEmpty()) {
            return listValues;
        }

        for (String value : getCensoredConfig().getStringList("CensoredWords.censored-word-list")) {
            listValues.add(value);
        }
        return listValues;
    }

    public boolean containCensoredWord(String str){
        List<String> words = getCensoredWords();
        String lowered = Utils.sanitizeString(str.toLowerCase());

        for (String check : words) {
            if (lowered.contains(check.toLowerCase())) {
                System.out.println("Someone said the word " + check);
                System.out.println("I have deleted their message! No need to worry");
                return true;
            }
        }
        return false;
    }

}
