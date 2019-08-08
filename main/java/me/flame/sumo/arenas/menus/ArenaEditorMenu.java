package me.flame.sumo.arenas.menus;

import me.flame.sumo.arenas.Arena;
import me.flame.sumo.arenas.ArenaLoader;
import me.flame.sumo.arenas.game.Game;
import me.flame.sumo.arenas.game.GameLoader;
import me.flame.sumo.utils.ChatUtils;
import me.flame.sumo.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ArenaEditorMenu {

    public Inventory arenaEditorMenu(Player p, String itemName) {

        Inventory editorMenu = Bukkit.createInventory(null, 9, ChatUtils.format("&3Editor &8| &f" + itemName));

        editorMenu.addItem(new ItemBuilder(Material.BED, 1)
                .setDisplayName("&cGo back")
                .setLore("", "&7Go back to the Arena list!").build());

        for (Arena arena : ArenaLoader.getInstance().getArenaList()) {
            if (arena.getName().contains(itemName)) {
                String pos1Result = arena.getPos1Set() ? "&atrue" : "&cfalse";
                String pos2Result = arena.getPos2Set() ? "&atrue" : "&cfalse";
                String activeResult = arena.getActive() ? "&aused" : "&cnot used";
                if (arena.getPos1Set() == false && arena.getPos2Set() == false) {
                    activeResult = ChatUtils.format("&6Incorrect setup");
                }

                // Arena Name Display
                editorMenu.setItem(2, new ItemBuilder(Material.ANVIL, 1)
                        .setDisplayName("&3Name: &f" + itemName)
                        .setLore(""
                                , " &b&oClick to change the name"
                                , ""
                                , " &b* &fYou will get an popup in your chat."
                                , " &b* &fIn the chat you can type the new name."
                                , " &b* &fInformation:"
                                , "   &3➥ &7Max. 10 characters long."
                                , "   &3➥ &7Color code supported."
                                , "").build());

                // Arena ID Display
                editorMenu.setItem(3, new ItemBuilder(Material.SIGN, 1)
                        .setDisplayName("&7ID: &f" + arena.getId()).build());

                // Arena Pos1 set Display
                editorMenu.setItem(4, new ItemBuilder(Material.ARMOR_STAND, 1)
                        .setDisplayName("&7Pos1 set: &f" + pos1Result)
                        .setLore(""
                                , " &b&oLeft click to change the position"
                                , "&b&oRight click to teleport to the location"
                                , ""
                                , " &b* &fThe position number 1,"
                                , " &b* &fOf the arena &3" + itemName + "&f,"
                                , " &b* &fWill be set to your current location!"
                                , "   &3➥ &7Currently set: " + pos1Result).build());

                // Arena pos2 set Display
                editorMenu.setItem(5, new ItemBuilder(Material.ARMOR_STAND, 1)
                        .setDisplayName("&7Pos2 set: &f" + pos2Result).setLore(""
                                , "   &b&oLeft click to change the position"
                                , "&b&oRight click to teleport to the location"
                                , "&b&o Shift click to remove this location!"
                                , ""
                                , " &b* &fThe position number 2,"
                                , " &b* &fOf the arena &3" + itemName + "&f,"
                                , " &b* &fWill be set to your current location!"
                                , "   &3➥ &7Currently set: " + pos2Result).build());

                // Arena current status Display
                editorMenu.setItem(6, new ItemBuilder(Material.NETHER_STAR, 1)
                        .setDisplayName("&7Status: " + activeResult).build());

                // Game Info
                for (Game game : GameLoader.getInstance().getGames()) {
                    if(game.isStarted()) {
                        if (game.getName().contains(itemName)) {
                            String started = game.isStarted() ? ChatUtils.format("&aStarted") : ChatUtils.format("&cNot started");
                            editorMenu.setItem(8, new ItemBuilder(Material.PAPER, 1)
                                    .setDisplayName("&b" + arena.getName() + "'s &7game info:")
                                    .setLore(""
                                            , "&7Started: " + started
                                            , "&7Player 1: &b" + game.getPlayer1().getName()
                                            , "&7Player 2: &b" + game.getPlayer2().getName()).build());
                        }
                    }
                }
            }
        }

        p.openInventory(editorMenu);
        editorMenu.setContents(editorMenu.getContents());
        return editorMenu;
    }
}
