package me.flame.sumo.listeners;

import me.flame.sumo.Core;
import me.flame.sumo.arenas.Arena;
import me.flame.sumo.arenas.ArenaLoader;
import me.flame.sumo.arenas.menus.ArenaEditorMenu;
import me.flame.sumo.utils.ChatUtils;
import me.flame.sumo.utils.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private ArenaEditorMenu arenaEditorMenu = new ArenaEditorMenu();

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent e)  {
        Player p = e.getPlayer();
        if (InventoryListener.inEditMode.containsKey(p.getUniqueId())) {
            e.setCancelled(true);
            if (e.getMessage().equalsIgnoreCase("cancel")) {
                arenaEditorMenu.arenaEditorMenu(p, InventoryListener.inEditMode.get(p.getUniqueId()));
                InventoryListener.inEditMode.remove(p.getUniqueId());
            } else {
                if (e.getMessage().getBytes().length > 10) {
                    p.sendMessage(ChatUtils.format("&3&l[Sumo] &7You may only use 10 charactes!"));
                } else if (!(e.getMessage().getBytes().length > 10)) {
                    for (Arena arena : ArenaLoader.getInstance().getArenaList()) {
                        if (arena.getName().contains(InventoryListener.inEditMode.get(p.getUniqueId()))) {
                            FileManager.get("arena.yml").set("arenas." + arena.getId() + ".name", e.getMessage());
                            FileManager.save(Core.getInstance(), "arena.yml");

                            p.sendMessage(ChatUtils.format("&3&l[Sumo] &7Use &b/sumo reload &7to apply the changes!"));
                            InventoryListener.inEditMode.remove(p.getUniqueId());
                        }
                    }
                }
            }
        }
    }
}
