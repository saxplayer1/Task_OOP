package gameUtil;

import units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private Faction faction;
    private String name;
    private List<Unit> army = new ArrayList<>();

    public List<Unit> getArmy() {
        return army;
    }

    public Faction getFaction() {
        return faction;
    }

    public String getName() {
        return name;
    }

    public Player(Faction faction, String name) {
        this.faction = faction;
        this.name = name;
    }


}
