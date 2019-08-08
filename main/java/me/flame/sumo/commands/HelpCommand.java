package me.flame.sumo.commands;

import me.flame.sumo.arenas.game.interfaces.GameManager;
import me.flame.sumo.commands.base.CommandBase;
import me.flame.sumo.commands.base.CommandHandler;
import me.flame.sumo.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class HelpCommand extends CommandBase {

    private final GameManager gameManager = new GameManager();

    public HelpCommand() {
        super("help", "Get a list of all the commands for this plugin", "", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) throws NotImplementedException {

        sender.sendMessage(ChatUtils.format("              &8[&3Sumo Plugin&8]"));
        sender.sendMessage(ChatUtils.format("    &7&o( All available commands )"));
        sender.sendMessage("");
        for (CommandBase commands : CommandHandler.getInstance().getCommands()) {
            sender.sendMessage(ChatUtils.format(" &3&l* &b/sumo " + commands.getName() + "&8 - &7" + commands.getDescription()));
        }

        sender.sendMessage("");
        sender.sendMessage(ChatUtils.format("              &8[&3Sumo Plugin&8]"));

    }
}
