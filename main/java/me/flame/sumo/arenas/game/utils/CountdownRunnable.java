package me.flame.sumo.arenas.game.utils;

import me.flame.sumo.Core;
import me.flame.sumo.utils.ChatUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CountdownRunnable {

    private HashMap<UUID, Integer> seconds = new HashMap<>();
    private HashMap<UUID, BukkitRunnable> runnable = new HashMap<>();
    public static List<UUID> inCountdown = new ArrayList<>();

    public void startGame(Player p1, Player p2){


        seconds.put(p1.getUniqueId(), 3);
        inCountdown.add(p1.getUniqueId());
        runnable.put(p1.getUniqueId(), new BukkitRunnable() {
            @Override
            public void run() {
                p1.sendMessage(ChatUtils.format("&3&l[Sumo] &7Starting in: &b" + seconds.get(p1.getUniqueId()) + "&7!"));
                p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 3);
                seconds.put(p1.getUniqueId(), seconds.get(p1.getUniqueId()) - 1);
                if(seconds.get(p1.getUniqueId()) == 0){
                    p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 5);
                    seconds.remove(p1.getUniqueId());
                    runnable.remove(p1.getUniqueId());
                    inCountdown.remove(p1.getUniqueId());
                    cancel();
                }
            }
        });

        seconds.put(p2.getUniqueId(), 3);
        inCountdown.add(p2.getUniqueId());
        runnable.put(p2.getUniqueId(), new BukkitRunnable() {
            @Override
            public void run() {
                p2.sendMessage(ChatUtils.format("&3&l[Sumo] &7Starting in: &b" + seconds.get(p2.getUniqueId()) + "&7!"));
                seconds.put(p2.getUniqueId(), seconds.get(p2.getUniqueId()) - 1);
                if(seconds.get(p2.getUniqueId()) == 0){
                    seconds.remove(p2.getUniqueId());
                    runnable.remove(p2.getUniqueId());
                    inCountdown.remove(p2.getUniqueId());
                    cancel();
                }
            }
        });

        runnable.get(p1.getUniqueId()).runTaskTimer(Core.getInstance(), 20, 20);
        runnable.get(p2.getUniqueId()).runTaskTimer(Core.getInstance(), 20, 20);
    }
}
