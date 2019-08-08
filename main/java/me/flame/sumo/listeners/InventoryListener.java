package me.flame.sumo.listeners;

import me.flame.sumo.Core;
import me.flame.sumo.arenas.Arena;
import me.flame.sumo.arenas.ArenaLoader;
import me.flame.sumo.arenas.menus.ArenaEditorMenu;
import me.flame.sumo.arenas.menus.ArenaListMenu;
import me.flame.sumo.utils.ChatUtils;
import me.flame.sumo.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.UUID;

public class InventoryListener implements Listener {

    private ArenaEditorMenu arenaEditorMenu = new ArenaEditorMenu();
    private ArenaListMenu arenaListMenu = new ArenaListMenu();
    public static HashMap<UUID, String> inEditMode = new HashMap<>();

    @EventHandler
    public void clickEvent(final InventoryClickEvent e) {
        if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta()) {
            return;
        }

        Player p = (Player) e.getWhoClicked();
        Integer slot = e.getSlot();
        String invName = e.getClickedInventory().getName();
        String name = e.getCurrentItem().getItemMeta().getDisplayName();

        if (invName.contains(ChatUtils.format("&3S &8| &7Loaded arena's"))) {
            e.setCancelled(true);
            arenaEditorMenu.arenaEditorMenu(p, name.replaceAll("§", "&"));
        } else if (invName.contains(ChatUtils.format("&3Editor &8| &f"))) {
            e.setCancelled(true);
            if (slot == 0) {
                arenaListMenu.openArenaListMenu(p);
            }
            if (slot == 2 && e.getCurrentItem().getType() == Material.ANVIL) {
                for (Arena arena : ArenaLoader.getInstance().getArenaList()) {
                    if (arena.getName().contains(invName.substring(15).replaceAll("§", "&"))) {
                        p.sendMessage(ChatUtils.format(""));
                        p.sendMessage(ChatUtils.format("&3&l✖ &7You are about to change the name of the arena with the ID &3" + arena.getId()));
                        p.sendMessage(ChatUtils.format("&3&l✖ &7The current name is: &b" + arena.getName()));
                        p.sendMessage(ChatUtils.format("&3&l✖ &7To leave this mode type: &3cancel&7."));
                        p.sendMessage(ChatUtils.format(""));
                        inEditMode.put(p.getUniqueId(), invName.substring(15).replaceAll("§", "&"));
                        p.closeInventory();
                    }
                }
            }
            if (slot == 4 && e.getCurrentItem().getType() == Material.ARMOR_STAND) {
                for (Arena arena : ArenaLoader.getInstance().getArenaList()) {
                    if (arena.getName().contains(invName.substring(15).replaceAll("§", "&"))) {
                        if (e.getClick().isShiftClick()) {
                            p.sendMessage(ChatUtils.format("&3&l[Sumo] &7Position 1 for arena &3" + invName.substring(15) + " &7has been removed!"));
                            FileManager.get("arena.yml").set("arenas." + arena.getId() + ".pos1Location", null);
                            FileManager.get("arena.yml").set("arenas." + arena.getId() + ".pos1", false);
                            FileManager.save(Core.getInstance(), "arena.yml");
                            p.closeInventory();
                        } else if (e.getClick().isLeftClick()) {
                            if (arena.getName().contains(invName.substring(15).replaceAll("§", "&"))) {
                                p.closeInventory();
                                p.sendMessage(ChatUtils.format("&3&l[Sumo] &7Position 1 for arena &3" + invName.substring(15) + " &7has been set to your location!"));
                                FileManager.get("arena.yml").set("arenas." + arena.getId() + ".pos1Location", p.getLocation().getWorld().getName()
                                        + ";" + p.getLocation().getX()
                                        + ";" + p.getLocation().getY()
                                        + ";" + p.getLocation().getZ()
                                        + ";" + p.getLocation().getYaw()
                                        + ";" + p.getLocation().getPitch());
                                FileManager.get("arena.yml").set("arenas." + arena.getId() + ".pos1", true);
                                FileManager.save(Core.getInstance(), "arena.yml");

                            }
                        } else if (e.getClick().isRightClick()) {
                            String loc = FileManager.get("arena.yml").getString("arenas." + arena.getId() + ".pos1Location");
                            String[] Test = loc.split(";");
                            p.teleport(new Location(Bukkit.getServer().getWorld(Test[0]), Double.valueOf(Test[1]), Double.valueOf(Test[2]), Double.valueOf(Test[3]), Float.valueOf(Test[4]), Float.valueOf(Test[5])));
                        }
                    }
                }
                ArenaLoader.LoadArena();
            }
            if (slot == 5 && e.getCurrentItem().getType() == Material.ARMOR_STAND) {
                for (Arena arena : ArenaLoader.getInstance().getArenaList()) {
                    if (arena.getName().contains(invName.substring(15).replaceAll("§", "&"))) {
                        if (e.getClick().isShiftClick()) {
                            p.sendMessage(ChatUtils.format("&3&l[Sumo] &7Position 2 for arena &3" + invName.substring(15) + " &7has been removed!"));
                            FileManager.get("arena.yml").set("arenas." + arena.getId() + ".pos2Location", null);
                            FileManager.get("arena.yml").set("arenas." + arena.getId() + ".pos2", false);
                            FileManager.save(Core.getInstance(), "arena.yml");
                            p.closeInventory();
                        } else if (e.getClick().isLeftClick()) {
                            if (arena.getName().contains(invName.substring(15).replaceAll("§", "&"))) {
                                p.closeInventory();
                                p.sendMessage(ChatUtils.format("&3&l[Sumo] &7Position 2 for arena &3" + invName.substring(15) + " &7has been set to your location!"));
                                FileManager.get("arena.yml").set("arenas." + arena.getId() + ".pos2Location", p.getLocation().getWorld().getName()
                                        + ";" + p.getLocation().getX()
                                        + ";" + p.getLocation().getY()
                                        + ";" + p.getLocation().getZ()
                                        + ";" + p.getLocation().getYaw()
                                        + ";" + p.getLocation().getPitch());
                                FileManager.get("arena.yml").set("arenas." + arena.getId() + ".pos2", true);
                                FileManager.save(Core.getInstance(), "arena.yml");

                            }
                        } else if (e.getClick().isRightClick()) {
                            String loc = FileManager.get("arena.yml").getString("arenas." + arena.getId() + ".pos2Location");
                            String[] Test = loc.split(";");
                            p.teleport(new Location(Bukkit.getServer().getWorld(Test[0]), Double.valueOf(Test[1]), Double.valueOf(Test[2]), Double.valueOf(Test[3]), Float.valueOf(Test[4]), Float.valueOf(Test[5])));
                        }
                    }
                }
                ArenaLoader.LoadArena();
            }
        }
    }
}