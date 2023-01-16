package me.r4wpanda.chatmanager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatToggle implements CommandExecutor, TabCompleter {

    private final ChatManagerPlugin main;

    public ChatToggle(ChatManagerPlugin main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("ChatManager.ChatToggle")) {
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage("Please choose how you want to toggle the chat");
            sender.sendMessage("Chat is currently " + main.chatEnabled);
            return false;
        }

        if (args[0].equalsIgnoreCase("on")) {
            if (main.chatEnabled) {
                sender.sendMessage("The chat is already on");
            } else {
                main.setChatEnabled(true);
                sender.sendMessage("You have enabled the chat");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("Chat has been enabled by " + sender.getName());
                }
            }
        } else if (args[0].equalsIgnoreCase("off")) {
            if (!main.chatEnabled) {
                sender.sendMessage("The chat is already off");
            } else {
                main.setChatEnabled(false);
                sender.sendMessage("You have disabled the chat");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("Chat has been disabled by " + sender.getName());
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("on", "off"), new ArrayList<>());
        }

        return new ArrayList<>();
    }
}