package me.r4wpanda.chatmanager.managers;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import me.r4wpanda.chatmanager.commands.ChatToggle;
import me.r4wpanda.chatmanager.listeners.PlayerEvents;
import org.bukkit.Bukkit;


import org.bukkit.command.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Locale;

public class RegisterManager {

    private final ChatManagerPlugin plugin = JavaPlugin.getPlugin(ChatManagerPlugin.class);

    public RegisterManager() {
        this.registerCommands();
        this.registerEvents();
    }

    private void registerCommands() {
        registerCommand("chattoggle", new ChatToggle());
    }
    private void registerEvents() {
        registerEvent(new PlayerEvents());
    }


    private void registerCommand(String commandName, CommandExecutor commandClass, TabCompleter seperateTabCompleteClass) {
        PluginCommand cmd = plugin.getCommand(commandName);
        cmd.setExecutor(commandClass);
        cmd.setTabCompleter(seperateTabCompleteClass);
    }

    private void registerCommand(String commandName, CommandExecutor commandClass) {
        PluginCommand cmd = plugin.getCommand(commandName);
        cmd.setExecutor(commandClass);
    }

    private void registerEvent(final Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
    }



}
