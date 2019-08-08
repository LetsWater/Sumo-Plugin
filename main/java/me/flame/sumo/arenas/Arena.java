package me.flame.sumo.arenas;

public class Arena {

    private String id;
    private String name;
    private boolean pos1Set;
    private boolean pos2Set;
    private boolean active;

    public Arena(String id, String name, boolean pos1Set, boolean pos2Set, boolean active) {
        this.id = id;
        this.name = name;
        this.pos1Set = pos1Set;
        this.pos2Set = pos2Set;
        this.active = active;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public boolean getPos1Set() {
        return pos1Set;
    }

    public boolean getPos2Set() {
        return pos2Set;
    }

    public boolean getActive() {
        return active;
    }
}
