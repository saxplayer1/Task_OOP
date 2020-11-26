package units;

import gameUtil.Player;

public class Winged extends Unit {
    public Winged(Player owner) {
        this.owner = owner;
        this.actionPoints = 4;
        this.atk = 3;
        this.hp = 3;
        this.type = UnitType.WINGED;
    }
}
