package me.flame.sumo.arenas.game;

import org.bukkit.entity.Player;

public class Game {

    private String id;
    private String name;
    private boolean started;
    private Player player1;
    private Player player2;

    public Game(String id, String name, boolean started, Player player1, Player player2) {
        this.id = id;
        this.name = name;
        this.started = started;
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isStarted() {
        return started;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
