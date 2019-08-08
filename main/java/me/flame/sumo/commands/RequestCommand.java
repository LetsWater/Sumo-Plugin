package me.flame.sumo.commands;

import me.flame.sumo.commands.base.CommandBase;
import me.flame.sumo.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestCommand extends CommandBase {

    public static List<Player> requested = new ArrayList<>();
    public static HashMap<Player, Player> inRequest = new HashMap<>();

    public RequestCommand() {
        super("request", "Request a player to join a Sumo game!", "sumo.duel", 0, 1, "duel", "invite");
    }


    @Override
    public void run(CommandSender sender, String[] args) throws NotImplementedException {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.format("&3&l[Sumo] &7This command may only be executed in-game!"));
            return;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(ChatUtils.format("&3&l[Sumo] &7Wrong arguments: &b/sumo request <player>"));
            return;
        }
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(ChatUtils.format("&3&l[Sumo] &7The player hasn't been found!"));
            return;
        }
        if(requested.contains(target)){
            p.sendMessage(ChatUtils.format("&3&l[Sumo] &7This player has already been requested!"));
            return;
        }
        if (args.length == 1) {
            p.sendMessage(ChatUtils.format("&3&l[Sumo] &7A game invite has succesfully been send to &b" + target.getName() + "&7!"));
            requested.add(p);


            target.sendMessage(ChatUtils.format("&3&l[Sumo] &b" + p.getName() + " &7invited you to join a Sumo game!"));
            target.sendMessage(ChatUtils.format("&a/sumo accept &7- to accept."));
            target.sendMessage(ChatUtils.format("&c/sumo decline &7- to decline"));
            target.sendMessage(ChatUtils.format("&7Request expires in &b60 &7seconds!"));
            requested.add(target);
            inRequest.put(target, p);
        }
    }
}
