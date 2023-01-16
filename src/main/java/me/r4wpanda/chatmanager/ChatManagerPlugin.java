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

        CensoredConfigManager censorManager = new CensoredConfigManager(this);

        Utils.registerManager(censorManager);
        registerCommands();
        registerEvents(censorManager);
    }

    public void setChatEnabled(boolean chatEnabled) {
        this.chatEnabled = chatEnabled;
    }

    public void registerCommands() {
        getCommand("chattoggle").setExecutor(new ChatToggle(this));
    }

    public void registerEvents(CensoredConfigManager censorManager) {
        Bukkit.getPluginManager().registerEvents(new ChatListener(censorManager), this);
    }

    public void mkDefDir() {
        if (!getDataFolder().exists()) {
            if (!getDataFolder().mkdir()) getLogger().severe("Could not create plugin directory!");
        }
    }
}