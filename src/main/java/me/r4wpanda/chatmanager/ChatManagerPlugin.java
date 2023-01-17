package me.r4wpanda.chatmanager;

import me.r4wpanda.chatmanager.config.BlacklistWordsHandler;
import me.r4wpanda.chatmanager.config.ConfigHandler;
import me.r4wpanda.chatmanager.managers.RegisterManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatManagerPlugin extends JavaPlugin {

    public boolean chatEnabled = true;

    public static ChatManagerPlugin instance;

    private ConfigHandler configHandler;
    private BlacklistWordsHandler blacklistWordsHandler;

    @Override
    public void onEnable() {

        instance = this;

        configHandler = new ConfigHandler(this)
                .createConfig("config")
                .createConfig("blacklistedWords");
        blacklistWordsHandler = new BlacklistWordsHandler(this);

        new RegisterManager();

    }

    public BlacklistWordsHandler getBlacklistWordsHandler () {return blacklistWordsHandler;}

    public ConfigHandler getConfigHandler () {
        return configHandler;
    }

    public void setChatEnabled(boolean chatEnabled) {
        this.chatEnabled = chatEnabled;
    }







}
