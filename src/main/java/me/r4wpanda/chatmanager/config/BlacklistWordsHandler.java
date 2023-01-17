package me.r4wpanda.chatmanager.config;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static me.r4wpanda.chatmanager.utils.Utils.desanitizeString;

public class BlacklistWordsHandler {

    private final ChatManagerPlugin plugin;
    private final HashSet<String> words;

    public BlacklistWordsHandler(ChatManagerPlugin plugin) {
        this.plugin = plugin;
        words = new HashSet<>();
        init();
    }

    private void init(){
        List<String> list = plugin.getConfigHandler()
                .getConfig("blacklistedWords")
                .getStringList("BlacklistedWords.list");
        list.forEach(this::addBlacklistedWord);
    }

    public void addBlacklistedWord(String word){
        words.add(word);
    }

    public void removeBlacklistedWord(String word){
        words.remove(word);
    }

    public HashSet<String> getWords(){
        return words;
    }

    public boolean hasBlacklistedWords(String str){
        HashSet<String> words = getWords();
        String lowered = desanitizeString(str.toLowerCase());

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
