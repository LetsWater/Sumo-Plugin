package me.flame.sumo.arenas.game.interfaces;

import me.flame.sumo.Core;
import me.flame.sumo.arenas.Arena;
import me.flame.sumo.arenas.ArenaLoader;
import me.flame.sumo.arenas.game.Game;
import me.flame.sumo.arenas.game.GameLoader;
import me.flame.sumo.arenas.game.utils.CountdownRunnable;
import me.flame.sumo.commands.RequestCommand;
import me.flame.sumo.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class GameManager implements GameInterface {
    private LinkedList<Game> availableGames = new LinkedList<>();
    public static List<UUID> inSumoGame = new ArrayList<>();
    final private CountdownRunnable countdownRunnable = new CountdownRunnable();

    @Override
    public void startGame(Player p1, Player p2) {
        Random random = new Random();
        for (Arena arena : ArenaLoader.getInstance().getArenaList()) {
            for (Game game : GameLoader.getInstance().getGames()) {
                if (!arena.getActive() && arena.getPos1Set() && arena.getPos2Set()) {
                    if (game.getName().equals(arena.getName())) {
                        availableGames.add(game);
                        Game choosedArena = availableGames.get(random.nextInt(availableGames.size()));
                        if (game == choosedArena) {

                            GameLoader.getInstance().reloadGame(game, choosedArena.getId(), choosedArena.getId(), true, p1, p2);
                            FileManager.get("arena.yml").set("arenas." + choosedArena.getId() + ".active", true);
                            FileManager.save(Core.getInstance(), "arena.yml");

                            p1.setGameMode(GameMode.ADVENTURE);
                            String loc = FileManager.get("arena.yml").getString("arenas." + arena.getId() + ".pos1Location");
                            String[] Location = loc.split(";");
                            p1.teleport(new Location(Bukkit.getServer().getWorld(Location[0]), Double.valueOf(Location[1]), Double.valueOf(Location[2]), Double.valueOf(Location[3]), Float.valueOf(Location[4]), Float.valueOf(Location[5])));
                            inSumoGame.add(p1.getUniqueId());

                            p2.setGameMode(GameMode.ADVENTURE);
                            String loc2 = FileManager.get("arena.yml").getString("arenas." + arena.getId() + ".pos2Location");
                            String[] Location2 = loc2.split(";");
                            p2.teleport(new Location(Bukkit.getServer().getWorld(Location2[0]), Double.valueOf(Location2[1]), Double.valueOf(Location2[2]), Double.valueOf(Location2[3]), Float.valueOf(Location2[4]), Float.valueOf(Location2[5])));
                            inSumoGame.add(p2.getUniqueId());

                            countdownRunnable.startGame(p1, p2);

                            ArenaLoader.LoadArena();
                            availableGames.clear();
                        }
                        return;
                    }
                }
            }
        }

    }

    @Override
    public void endGame(Player p1) {
        for (Arena arena : ArenaLoader.getInstance().getArenaList()) {
            for (Game game : GameLoader.getInstance().getGames()) {
                if (arena.getActive() && arena.getPos1Set() && arena.getPos2Set()) {
                    if (game.getPlayer1() == p1) {
                        FileManager.get("arena.yml").set("arenas." + game.getId() + ".active", false);
                        FileManager.save(Core.getInstance(), "arena.yml");

                        inSumoGame.remove(game.getPlayer1().getUniqueId());
                        RequestCommand.requested.remove(game.getPlayer1());
                        String loc = FileManager.get("config.yml").getString("sumo.spawn.location");
                        String[] Location = loc.split(";");
                        game.getPlayer1().teleport(new Location(Bukkit.getServer().getWorld(Location[0]), Double.valueOf(Location[1]), Double.valueOf(Location[2]), Double.valueOf(Location[3]), Float.valueOf(Location[4]), Float.valueOf(Location[5])));

                        inSumoGame.remove(game.getPlayer2().getUniqueId());
                        RequestCommand.requested.remove(game.getPlayer2());
                        RequestCommand.inRequest.remove(game.getPlayer2());
                        String loc2 = FileManager.get("config.yml").getString("sumo.spawn.location");
                        String[] Location2 = loc2.split(";");
                        game.getPlayer2().teleport(new Location(Bukkit.getServer().getWorld(Location2[0]), Double.valueOf(Location2[1]), Double.valueOf(Location2[2]), Double.valueOf(Location2[3]), Float.valueOf(Location2[4]), Float.valueOf(Location2[5])));

                        GameLoader.getInstance().LoadGames();
                        ArenaLoader.LoadArena();
                        return;
                    }
                }
            }
        }
    }
}