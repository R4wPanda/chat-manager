package me.r4wpanda.chatmanager;

import me.r4wpanda.chatmanager.managers.CensoredConfigManager;
import me.r4wpanda.chatmanager.managers.RegisterManager;
import me.r4wpanda.chatmanager.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatManagerPlugin extends JavaPlugin {

    public boolean chatEnabled;
    public static ChatManagerPlugin instance;
    public CensoredConfigManager CCManager;

    @Override
    public void onEnable() {

        chatEnabled = true;
        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        CCManager = new CensoredConfigManager();;
        new RegisterManager();

    }

    public static ChatManagerPlugin getInstance() {
        return instance;
    }

    public void setChatEnabled(boolean chatEnabled) {
        this.chatEnabled = chatEnabled;
    }

    public void mkDefDir() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
    }






}
