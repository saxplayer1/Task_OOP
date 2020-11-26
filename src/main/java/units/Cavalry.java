package units;

import gameUtil.Player;

public class Cavalry extends Unit {
    public Cavalry(Player owner) {
        this.owner = owner;
        this.actionPoints = 5;
        this.atk = 5;
        this.hp = 5;
        this.type = UnitType.CAVALRY;
    }
}
