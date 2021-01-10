package gameUtil;

import units.Unit;
import units.UnitType;

public class Vertex {
    private int index;
    private UnitType type;
    private Unit unit;

    public Vertex(int index, UnitType type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public UnitType getType() {
        return type;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String typeToString(){
        switch (this.type){
            case WINGED:
                return "WINGED";
            case CAVALRY:
                return "CAVALRY";
            case FOOTMAN:
                return "FOOTMAN";
        }
        return "";
    }
}
