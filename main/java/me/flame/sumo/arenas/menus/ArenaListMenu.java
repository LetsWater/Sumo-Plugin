package me.flame.sumo.arenas.menus;

import me.flame.sumo.arenas.Arena;
import me.flame.sumo.arenas.ArenaLoader;
import me.flame.sumo.utils.ChatUtils;
import me.flame.sumo.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ArenaListMenu {

    public Inventory openArenaListMenu(Player p) {
        Inventory arenaList = Bukkit.createInventory(null, 54, ChatUtils.format("&3S &8| &7Loaded arena's"));

        for (Arena arena : ArenaLoader.getInstance().getArenaList()) {
            String pos1Result = arena.getPos1Set() ? "&atrue" : "&cfalse";
            String pos2Result = arena.getPos2Set() ? "&atrue" : "&cfalse";
            String activeResult = arena.getActive() ? "&aused" : "&cnot used";
            if (!arena.getPos1Set() || !arena.getPos2Set()) {
                activeResult = ChatUtils.format("&6Incorrect setup");
            }

            String id = arena.getId();
            arenaList.addItem(new ItemBuilder(Material.IRON_BLOCK, Integer.valueOf(id.substring(1)))
                    .setDisplayName(arena.getName())
                    .setLore("", "&7ID: &3"
                            + arena.getId(), "&7Pos1 set: "
                            + pos1Result, "&7Pos2 set: "
                            + pos2Result, "&7Status: "
                            + activeResult).build());

        }

        p.openInventory(arenaList);
        arenaList.setContents(arenaList.getContents());
        return arenaList;
    }
}
