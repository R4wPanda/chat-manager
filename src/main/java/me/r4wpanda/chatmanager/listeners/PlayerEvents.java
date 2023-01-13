package me.r4wpanda.chatmanager.listeners;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import me.r4wpanda.chatmanager.managers.CensoredConfigManager;
import me.r4wpanda.chatmanager.utils.ConfigUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerEvents implements Listener {

    private ChatManagerPlugin main = ChatManagerPlugin.getInstance();
    private CensoredConfigManager CCManager = CensoredConfigManager.getInstance();

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {

        if (!main.chatEnabled){
            if (!e.getPlayer().hasPermission("ChatManager.ChatToggleBypass")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("The chat is currently disabled");
            }
        }

        if (!ConfigUtils.checkFeatureToggle("word-censur")) {
            return;
        }

        if (CCManager.containCensoredWord(e.getMessage())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("Your message have been deleted, as it contains a blacklisted word!");
        }
    }
}
