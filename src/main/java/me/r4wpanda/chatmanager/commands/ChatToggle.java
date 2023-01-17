package me.r4wpanda.chatmanager.commands;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import me.r4wpanda.chatmanager.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatToggle implements CommandExecutor, TabCompleter {

    private final ChatManagerPlugin plugin = JavaPlugin.getPlugin(ChatManagerPlugin.class);



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("ChatManager.ChatToggle")) {
            return true;
        }

        if (!Utils.checkFeatureToggle("chat-toggle")) {
            sender.sendMessage("Chat toggling is currently disabled in config.yml");
            return true;
        }

        if (args.length == 0) {
            return false;
        }

        if (args[0].equalsIgnoreCase("on")) {
            if (plugin.chatEnabled) {
                sender.sendMessage("The chat is already on");
            } else {
                plugin.setChatEnabled(true);
                sender.sendMessage("You have enabled the chat");
                for (Player player : Bukkit.getOnlinePlayers())  {player.sendMessage("Chat has been enabled by " + sender.getName());}
            }
        } else if (args[0].equalsIgnoreCase("off")) {
            if (!plugin.chatEnabled) {
                sender.sendMessage("The chat is already off");
            } else {
                plugin.setChatEnabled(false);
                sender.sendMessage("You have disabled the chat");
                for (Player player : Bukkit.getOnlinePlayers())  {player.sendMessage("Chat has been disabled by " + sender.getName());}
            }
        } else if (args[0].equalsIgnoreCase("status")){
            sender.sendMessage(plugin.chatEnabled ? "Server chat is currently" + ChatColor.GREEN + " enabled" : "Server chat is currently" + ChatColor.RED + " disabled");
            return true;
        }
        return true;
    }


        @Override
        public List<String> onTabComplete (CommandSender sender, Command command, String alias, String[]args){
            if (args.length == 1) {
                return StringUtil.copyPartialMatches(args[0], Arrays.asList("on", "off", "status"), new ArrayList<>());
            }

            return null;
        }
    }

