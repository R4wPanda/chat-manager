package me.r4wpanda.chatmanager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final CensoredConfigManager censorManager;

    public ChatListener(CensoredConfigManager censorManager) {
        this.censorManager = censorManager;
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {

        if (!censorManager.main.chatEnabled) {
            if (!e.getPlayer().hasPermission("ChatManager.ChatToggleBypass")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("The chat is currently disabled");
            }
        }

        if (!censorManager.checkCensoredToggle()) {
            return;
        }

        if (censorManager.containCensoredWord(e.getMessage())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("Your message have been deleted, as it contains a blacklisted word!");
        }
    }
}