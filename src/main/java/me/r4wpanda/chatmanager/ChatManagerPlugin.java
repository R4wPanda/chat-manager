package me.r4wpanda.chatmanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatManagerPlugin extends JavaPlugin {

    public boolean chatEnabled = true;

    @Override
    public void onEnable() {
        mkDefDir();
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        CensoredConfigManager CCManager = new CensoredConfigManager(this);;
        registerOther(CCManager);
        registerCommands();
        registerEvents(CCManager);
    }


    public void setChatEnabled(boolean chatEnabled) {
        this.chatEnabled = chatEnabled;
    }

    public void registerOther(CensoredConfigManager CCManager) {
        new Utils(CCManager);
    }

    public void registerCommands() {
        ChatToggle chatToggle = new ChatToggle(this);
        getCommand("chattoggle").setExecutor(chatToggle);
        getCommand("chattoggle").setTabCompleter(chatToggle);
    }

    public void registerEvents(CensoredConfigManager CCManager) {
        Bukkit.getPluginManager().registerEvents(new ChatListener(CCManager), this);
    }

    public void mkDefDir() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
    }






}
