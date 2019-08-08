package me.flame.sumo.arenas;

import me.flame.sumo.utils.FileManager;

import java.util.ArrayList;
import java.util.List;

public class ArenaLoader {

    private static ArenaLoader instance = new ArenaLoader();
    public static List<Arena> arenas = new ArrayList<>();
    public static List<String> createdArenas = new ArrayList<>();

    public static void LoadArena() {
        arenas.clear();
        createdArenas.clear();
        for (String key : FileManager.get("arena.yml").getConfigurationSection("arenas").getKeys(false)) {
            Arena arena;
            String id = FileManager.get("arena.yml").getString("arenas." + key + ".id");
            String name = FileManager.get("arena.yml").getString("arenas." + key + ".name");
            boolean pos1 = FileManager.get("arena.yml").getBoolean("arenas." + key + ".pos1");
            boolean pos2 = FileManager.get("arena.yml").getBoolean("arenas." + key + ".pos2");
            boolean active = FileManager.get("arena.yml").getBoolean("arenas." + key + ".active");
            arena = new Arena(id, name, pos1, pos2, active);
            arenas.add(arena);
            createdArenas.add(id);
        }
    }

    public void reloadArena(Arena arena, String id){
        if(arenas.contains(arena)){
            arenas.remove(arena);
            createdArenas.remove(id);
        }

        if (!arenas.contains(arena)) {
            String name = FileManager.get("arena.yml").getString("arenas." + id + ".name");
            boolean pos1 = FileManager.get("arena.yml").getBoolean("arenas." + id + ".pos1");
            boolean pos2 = FileManager.get("arena.yml").getBoolean("arenas." + id + ".pos2");
            boolean active = FileManager.get("arena.yml").getBoolean("arenas." + id + ".active");
            arena = new Arena(id, name, pos1, pos2, active);

            arenas.add(arena);
            createdArenas.add(id);
        }
    }

    public List<Arena> getArenaList() {
        return arenas;
    }

    public static ArenaLoader getInstance() {
        return instance;
    }

    public List<String> getCreatedArenas() {
        return createdArenas;
    }
}
