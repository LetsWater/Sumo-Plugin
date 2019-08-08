package me.flame.sumo.arenas.game.listeners;

import me.flame.sumo.arenas.game.Game;
import me.flame.sumo.arenas.game.GameLoader;
import me.flame.sumo.arenas.game.interfaces.GameManager;
import me.flame.sumo.arenas.game.utils.CountdownRunnable;
import me.flame.sumo.utils.FileManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class GameListener implements Listener {

    final private GameManager gameManager = new GameManager();

    @EventHandler(priority = EventPriority.MONITOR)
    public void gameEvent(final PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (GameManager.inSumoGame.contains(p.getUniqueId())) {
            for (Game game : GameLoader.getInstance().getGames()) {
                if (game.getPlayer1() == p) {
                    String loc = FileManager.get("arena.yml").getString("arenas." + game.getId() + ".pos1Location");
                    String[] Location = loc.split(";");
                    if(p.getLocation().getY() <= Double.parseDouble(Location[2]) - 2){
                        p.sendMessage("U lost");
                        game.getPlayer2().sendMessage("U won");
                        gameManager.endGame(game.getPlayer1());
                    }
                    return;
                } if (game.getPlayer2() == p) {
                    String loc = FileManager.get("arena.yml").getString("arenas." + game.getId() + ".pos2Location");
                    String[] Location = loc.split(";");
                    if(p.getLocation().getY() <= Double.parseDouble(Location[2]) - 2){
                        p.sendMessage("U lost");
                        game.getPlayer1().sendMessage("U won");
                        gameManager.endGame(game.getPlayer1());
                    }
                }
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void inCountdownMove(final PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(CountdownRunnable.inCountdown.contains(p.getUniqueId())){
            e.setTo(p.getLocation());
            e.setCancelled(true);
        }

        if(GameManager.inSumoGame.contains(p.getUniqueId())){
            p.setFoodLevel(20);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void DamageEvent(final EntityDamageEvent e){
        if(e.getEntityType() != EntityType.PLAYER) return;

        Player p = (Player) e.getEntity();
        if(GameManager.inSumoGame.contains(p.getUniqueId())){
            p.setHealth(20);
        }
    }
}
