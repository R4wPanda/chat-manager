package me.r4wpanda.chatmanager.config;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;

public class ConfigHandler {


    private final ChatManagerPlugin plugin;
    private final HashMap<String, YamlConfiguration> configs;

    public ConfigHandler(ChatManagerPlugin plugin){
        this.plugin = plugin;
        configs = new HashMap<>();
        init();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void init(){
        if (!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdirs();
        }
    }

    public ConfigHandler createConfig(String name){
        File file = new File(plugin.getDataFolder(), name + ".yml");
        InputStream inputStream = plugin.getResource(name + ".yml");

        if (!file.exists()){

            try {
                assert inputStream != null;
                Files.copy(inputStream, file.toPath());
            } catch (IOException exception){
                exception.printStackTrace();
            }


        }

        saveConfig(name, YamlConfiguration.loadConfiguration(file));
        return this;
    }

    public void saveConfig(String name, YamlConfiguration config){
        configs.put(name, config);
    }

    public YamlConfiguration getConfig(String name){
        return configs.get(name);
    }



}
