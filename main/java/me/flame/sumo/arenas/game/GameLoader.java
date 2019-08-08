package me.flame.sumo.arenas.game;

import me.flame.sumo.arenas.Arena;
import me.flame.sumo.arenas.ArenaLoader;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameLoader {

    private static GameLoader instance = new GameLoader();
    public static List<Game> games = new ArrayList<>();

    public void LoadGames() {
        for (Arena arena : ArenaLoader.getInstance().getArenaList()) {
            Game game;
            String id = arena.getId();
            String name = arena.getName();
            game = new Game(id, name, false, null, null);
            games.add(game);
        }
    }

    public void reloadGame(Game game, String id, String name, Boolean started, Player player1, Player player2) {
        if (games.contains(game)) {
            games.remove(game);
        }

        if (!games.contains(game)) {
            game = new Game(id, name, started, player1, player2);
            games.add(game);
        }
    }

    public static GameLoader getInstance() {
        return instance;
    }

    public List<Game> getGames() {
        return games;
    }
}
