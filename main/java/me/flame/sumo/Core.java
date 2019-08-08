package me.flame.sumo;

import me.flame.sumo.arenas.ArenaLoader;
import me.flame.sumo.arenas.game.GameLoader;
import me.flame.sumo.arenas.game.listeners.GameListener;
import me.flame.sumo.commands.base.CommandHandler;
import me.flame.sumo.listeners.ChatListener;
import me.flame.sumo.listeners.InventoryListener;
import me.flame.sumo.utils.ChatUtils;
import me.flame.sumo.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin implements Listener {

    private static Core instance;
    private CommandHandler commandHandler;
    private ArenaLoader arenaLoader = new ArenaLoader();
    private GameLoader gameLoader = new GameLoader();

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        registerListener();

        commandHandler = new CommandHandler();
        commandHandler.enable(this);

        ArenaLoader.LoadArena();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatUtils.format("&3&l[Sumo] &f&lEr zijn &b" + arenaLoader.getArenaList().size() + " &7arena(s) geladen!"));

        gameLoader.LoadGames();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatUtils.format("&3&l[Sumo] &fI have also loaded &b" + gameLoader.getGames().size() + " &fgames!"));
    }

    @Override
    public void onDisable() {
        instance = null;
        commandHandler.disable(this);
    }

    private void registerListener() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(this, this);
        pm.registerEvents(new InventoryListener(), this);
        pm.registerEvents(new ChatListener(), this);

        pm.registerEvents(new GameListener(), this);
    }

    private void loadConfig() {
        FileManager.load(this, "arena.yml");
        FileManager.load(this, "config.yml");
    }
}
