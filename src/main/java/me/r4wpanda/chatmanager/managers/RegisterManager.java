package me.r4wpanda.chatmanager.managers;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import me.r4wpanda.chatmanager.commands.ChatToggle;
import me.r4wpanda.chatmanager.listeners.PlayerEvents;
import org.bukkit.Bukkit;


import org.bukkit.command.*;
import org.bukkit.event.Listener;

import java.lang.reflect.Field;

public class RegisterManager {

    private ChatManagerPlugin main = ChatManagerPlugin.getInstance();

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
        PluginCommand cmd = main.getCommand(commandName);
        cmd.setExecutor(commandClass);
        cmd.setTabCompleter(seperateTabCompleteClass);
    }

    private void registerCommand(String commandName, CommandExecutor commandClass) {
        PluginCommand cmd = main.getCommand(commandName);
        cmd.setExecutor(commandClass);
    }


    private void registerEvent(final Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, ChatManagerPlugin.getInstance());
    }



}
