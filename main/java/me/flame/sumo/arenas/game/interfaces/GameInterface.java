package me.flame.sumo.arenas.game.interfaces;

import org.bukkit.entity.Player;

public interface GameInterface {

    /**
     * This will start the gamemode, and changes all the settings.
     *
     * @param p1 Player p1
     * @param p2 Player p2
     */
    void startGame(Player p1, Player p2);

    /**
     * This will end the gamemode, and set all the settings back to basic.
     * Using the Player 1 to get the correct Game & Arena.
     *
     * @param p1 Player 1
     */
    void endGame(Player p1);
}
