package me.r4wpanda.chatmanager.listeners;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import me.r4wpanda.chatmanager.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerEvents implements Listener {

    private final ChatManagerPlugin plugin = JavaPlugin.getPlugin(ChatManagerPlugin.class);

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {

        if (!plugin.chatEnabled && !e.getPlayer().hasPermission("ChatManager.ChatToggle.Bypass")){
            System.out.println("DISABLED");
            e.setCancelled(true);
            e.getPlayer().sendMessage("The chat is currently disabled");
        }

        if (Utils.checkFeatureToggle("word-blacklist") && plugin.getBlacklistWordsHandler().hasBlacklistedWords(e.getMessage())) {
            System.out.println("That message has a blacklisted word");
            e.setCancelled(true);
            e.getPlayer().sendMessage("Your message have been deleted, as it contains a blacklisted word!");
        }
    }
}
