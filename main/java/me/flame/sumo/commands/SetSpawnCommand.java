package me.flame.sumo.commands;

import me.flame.sumo.Core;
import me.flame.sumo.commands.base.CommandBase;
import me.flame.sumo.utils.ChatUtils;
import me.flame.sumo.utils.FileManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SetSpawnCommand extends CommandBase {

    public SetSpawnCommand() {
        super("setspawn", "Set the spawn of this gamemode!", "sumo.setspawn", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) throws NotImplementedException {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatUtils.format("&3&l[Sumo] &7You may only use this command in-game!"));
        }

        Player p = (Player) sender;
        FileManager.get("config.yml").set("sumo.spawn.location", p.getLocation().getWorld().getName()
                + ";" + p.getLocation().getX()
                + ";" + p.getLocation().getY()
                + ";" + p.getLocation().getZ()
                + ";" + p.getLocation().getYaw()
                + ";" + p.getLocation().getPitch());
        FileManager.save(Core.getInstance(), "config.yml");

        p.sendMessage(ChatUtils.format("&3&l[Sumo] &7You have succesfully set the spawn of the Sumo plugin!"));
    }
}
