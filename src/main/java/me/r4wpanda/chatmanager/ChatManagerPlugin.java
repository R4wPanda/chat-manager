package me.r4wpanda.chatmanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatManagerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        mkDefDir();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        CensoredConfigManager CCManager = new CensoredConfigManager(this);

        Bukkit.getPluginManager().registerEvents(new ChatListener(CCManager), this);


    }



    public void mkDefDir() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
    }






}
