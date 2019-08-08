package me.flame.sumo.commands;

import me.flame.sumo.Core;
import me.flame.sumo.arenas.ArenaLoader;
import me.flame.sumo.arenas.menus.ArenaListMenu;
import me.flame.sumo.commands.base.CommandBase;
import me.flame.sumo.utils.ChatUtils;
import me.flame.sumo.utils.FileManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ArenaCommand extends CommandBase {

    private ArenaListMenu arenaListMenu = new ArenaListMenu();

    public ArenaCommand() {
        super("arena", "The arena manager command", "sumo.arena.manage", 0, 2);
    }


    @Override
    public void run(CommandSender sender, String[] args) throws NotImplementedException {
        if (args.length == 0) {
            sender.sendMessage(ChatUtils.format("              &8[&3Sumo Plugin&8]"));
            sender.sendMessage(ChatUtils.format("  &7&o( All available Arena commands )"));
            sender.sendMessage("");
            sender.sendMessage(ChatUtils.format(" &b&l* &7/sumo &barena help"));
            sender.sendMessage(ChatUtils.format(" &b&l* &7/sumo &barena list"));
            sender.sendMessage("");
            sender.sendMessage(ChatUtils.format("              &8[&3Sumo Plugin&8]"));
            return;
        }
        switch (args[0].toLowerCase()) {
            case "list":
                if (!(sender instanceof Player)) break;

                Player p = (Player) sender;
                sender.sendMessage(ChatUtils.format("&3&l[Sumo] &7Loading all the arenas in a menu."));
                arenaListMenu.openArenaListMenu(p);
                break;
            case "create":
            case "add":
                if (args.length == 1) {
                    sender.sendMessage(ChatUtils.format("&3&l[Sumo] &7Command usage: &7/sumo arena create <ID>"));
                    break;
                }
                if (args.length == 2) {
                    if (ArenaLoader.getInstance().getCreatedArenas().contains("A" + args[1])) {
                        sender.sendMessage(ChatUtils.format("&3&l[Sumo] &7There is already a arena created with the ID you entered! Please choose another one."));
                        break;
                    }
                    sender.sendMessage(ChatUtils.format("&3&l[Sumo] &7You have succesfully created an arena with the ID: &bA" + args[1] + "&7!"));
                    FileManager.get("arena.yml").set("arenas.A" + args[1] + ".id", "A" + args[1]);
                    FileManager.get("arena.yml").set("arenas.A" + args[1] + ".name", "&4Arena " + args[1]);
                    FileManager.get("arena.yml").set("arenas.A" + args[1] + ".pos1", false);
                    FileManager.get("arena.yml").set("arenas.A" + args[1] + ".pos2", false);
                    FileManager.get("arena.yml").set("arenas.A" + args[1] + ".active", false);
                    FileManager.save(Core.getInstance(), "arena.yml");

                    ArenaLoader.LoadArena();
                    break;
                }
                break;
            case "delete":
            case "remove":
                if (args.length == 1) {
                    sender.sendMessage(ChatUtils.format("&3&l[Sumo] &7Command usage: &7/sumo arena delete <ID>"));
                    break;
                }
                if (args.length == 2) {
                    if (!ArenaLoader.getInstance().getCreatedArenas().contains(args[1])) {
                        sender.sendMessage(ChatUtils.format("&3&l[Sumo] &7The ID you entered isn't correct!"));
                        break;
                    }
                    sender.sendMessage(ChatUtils.format("&3&l[Sumo] &7You have succesfully deleted the arena with the ID: &b" + args[1] + "&7!"));
                    FileManager.set("arena.yml", "arenas." + args[1], null);
                    FileManager.save(Core.getInstance(), "arena.yml");

                    ArenaLoader.LoadArena();
                    break;
                }
            default:
                sender.sendMessage(ChatUtils.format("              &8[&3Sumo Plugin&8]"));
                sender.sendMessage(ChatUtils.format("    &7&o( All available Arena commands )"));
                sender.sendMessage("");
                sender.sendMessage(ChatUtils.format(" &b&l* &7/arena &bhelp"));
                sender.sendMessage("");
                sender.sendMessage(ChatUtils.format("              &8[&3Sumo Plugin&8]"));
                break;
        }
    }
}
