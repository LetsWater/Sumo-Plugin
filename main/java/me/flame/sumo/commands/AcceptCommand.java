package me.flame.sumo.commands;

import me.flame.sumo.arenas.game.interfaces.GameManager;
import me.flame.sumo.commands.base.CommandBase;
import me.flame.sumo.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AcceptCommand extends CommandBase {

    final private GameManager gameManager = new GameManager();

    public AcceptCommand() {
        super("accept", "All information about the plugin", "", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) throws NotImplementedException {

        Player p = (Player) sender;
        if(RequestCommand.inRequest.containsKey(p)){
            Player player1 = RequestCommand.inRequest.get(p);
            player1.sendMessage(ChatUtils.format("&3&l[Sumo] &7Request has been accepted! Game is starting soon!"));
            p.sendMessage(ChatUtils.format("&3&l[Sumo] &7You have accepted the request! Game is starting soon."));

            gameManager.startGame(player1, p);
        }
    }
}
