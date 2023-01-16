package me.r4wpanda.chatmanager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final CensoredConfigManager CCManager;

    public ChatListener(CensoredConfigManager CCManager) {
        this.CCManager = CCManager;
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {

        if (!CCManager.main.chatEnabled) {
            if (!e.getPlayer().hasPermission("ChatManager.ChatToggleBypass")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("The chat is currently disabled");
            }
        }

        if (!CCManager.checkCensoredToggle()) {
            return;
        }

        if (CCManager.containCensoredWord(e.getMessage())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("Your message have been deleted, as it contains a blacklisted word!");
        }
    }
}