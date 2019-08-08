package me.flame.sumo.commands;

import me.flame.sumo.Core;
import me.flame.sumo.arenas.ArenaLoader;
import me.flame.sumo.commands.base.CommandBase;
import me.flame.sumo.utils.FileManager;
import me.flame.sumo.utils.PluginUtils;
import org.bukkit.command.CommandSender;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ReloadCommand extends CommandBase {

    public ReloadCommand() {
        super("reload", "Reload command", "", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) throws NotImplementedException {
        FileManager.configs.clear();
        FileManager.load(Core.getInstance(), "arena.yml");
        PluginUtils.restartPlugin(Core.getInstance());
        ArenaLoader.LoadArena();
        sender.sendMessage("reload");
    }
}
