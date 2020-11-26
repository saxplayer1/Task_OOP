package units;

import gameUtil.Faction;
import gameUtil.Player;

public class Unit {
    protected int actionPoints;
    protected int hp;
    protected int atk;
    protected UnitType type;
    protected int position;
    protected Player owner;

    public Player getOwner() {
        return owner;
    }

    public Faction getFaction() {
        return this.getOwner().getFaction();
    }

    public int getPosition() {
        return position;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public UnitType getType() {
        return type;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
