package me.r4wpanda.chatmanager;

import java.util.HashMap;
import java.util.List;

public class Utils {

    private static HashMap<Character, String> replacementMap = new HashMap<>();

    public static String numberedWordReplacer(String word) {

        replacementMap.put('1', "i");
        replacementMap.put('2', "z");
        replacementMap.put('3', "e");
        replacementMap.put('4', "a");
        replacementMap.put('5', "s");
        replacementMap.put('6', "g");
        replacementMap.put('7', "t");
        replacementMap.put('8', "b");
        replacementMap.put('9', "q");
        replacementMap.put('0', "o");

        StringBuilder output = new StringBuilder();

        for (char c : word.toCharArray()) {
            if (replacementMap.containsKey(c)) {
                output.append(replacementMap.get(c));
            } else {
                output.append(c);
            }
        }

        return output.toString();
    }
}
