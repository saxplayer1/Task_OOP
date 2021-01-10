package gameUtil;

import units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    protected Faction faction;
    protected String name;
    protected List<Unit> army = new ArrayList<>();

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

    public String factionToString(){
        switch(this.faction) {
            case RED:
                return "RED";
            case BLUE:
                return "BLUE";
            case GREEN:
                return "GREEN";
            case PURPLE:
                return "PURPLE";
        }
        return "";
    }
}
