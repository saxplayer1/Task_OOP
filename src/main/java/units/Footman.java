package units;

import gameUtil.Player;

public class Footman extends Unit {
    public Footman(Player owner) {
        this.owner = owner;
        this.actionPoints = 2;
        this.atk = 3;
        this.hp = 3;
        this.type = UnitType.FOOTMAN;
    }
}
