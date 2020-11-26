package unitService;

import gameUtil.Faction;
import gameUtil.Field;
import units.Unit;
import units.Winged;

import java.util.Random;

public class WingedAction implements UnitAction {
    Random rnd = new Random();

    @Override
    public void move(Unit unit, int mp, Field field) {
        int size = field.getSize() * field.getSize();
        int length = field.getSize();

        for (int i = 0; i < mp; i++) {
            int pos = unit.getPosition();
            int check = checkEnemy(unit,pos,field);
            if (check == 0) {
                if (unit.getFaction() == Faction.RED) {
                    if (pos + length < size) {
                        if (field.get(pos + length).getUnit() == null) {
                            field.moveUnit(pos, pos + length);
                            continue;
                        }
                    }
                    if (pos + 1 < size && (pos) % length != 0) {
                        if (field.get(pos + 1).getUnit() == null) {
                            field.moveUnit(pos, pos + 1);
                            continue;
                        }
                    }
                    if (pos - 1 > 0 && (pos - 1) % length != 0) {
                        if (field.get(pos - 1).getUnit() == null) {
                            field.moveUnit(pos, pos - 1);
                            continue;
                        }
                    }
                    if (pos - length > 0) {
                        if (field.get(pos - length).getUnit() == null) {
                            field.moveUnit(pos, pos - length);
                            continue;
                        }
                    }
                }


                if (unit.getFaction() == Faction.BLUE) {
                    if (pos - length > 0) {
                        if (field.get(pos - length).getUnit() == null) {
                            field.moveUnit(pos, pos - length);
                            continue;
                        }
                    }
                    if (pos - 1 > 0 && (pos - 1) % length != 0) {
                        if (field.get(pos - 1).getUnit() == null) {
                            field.moveUnit(pos, pos - 1);
                            continue;
                        }
                    }
                    if (pos + 1 < size && (pos) % length != 0) {
                        if (field.get(pos + 1).getUnit() == null) {
                            field.moveUnit(pos, pos + 1);
                            continue;
                        }
                    }
                    if (pos + length < size) {
                        if (field.get(pos + length).getUnit() == null) {
                            field.moveUnit(pos, pos + length);
                            continue;
                        }
                    }
                }

                if (unit.getFaction() == Faction.GREEN) {
                    if (pos + 1 < size && (pos) % length != 0) {
                        if (field.get(pos + 1).getUnit() == null) {
                            field.moveUnit(pos, pos + 1);
                            continue;
                        }
                    }
                    if (pos - length > 0) {
                        if (field.get(pos - length).getUnit() == null) {
                            field.moveUnit(pos, pos - length);
                            continue;
                        }
                    }
                    if (pos + length < size) {
                        if (field.get(pos + length).getUnit() == null) {
                            field.moveUnit(pos, pos + length);
                            continue;
                        }
                    }
                    if (pos - 1 > 0 && (pos - 1) % length != 0) {
                        if (field.get(pos - 1).getUnit() == null) {
                            field.moveUnit(pos, pos - 1);
                            continue;
                        }
                    }
                }

                if (unit.getFaction() == Faction.PURPLE) {
                    if (pos - 1 > 0 && (pos - 1) % length != 0) {
                        if (field.get(pos - 1).getUnit() == null) {
                            field.moveUnit(pos, pos - 1);
                            continue;
                        }
                    }
                    if (pos - length > 0) {
                        if (field.get(pos - length).getUnit() == null) {
                            field.moveUnit(pos, pos - length);
                            continue;
                        }
                    }
                    if (pos + length < size) {
                        if (field.get(pos + length).getUnit() == null) {
                            field.moveUnit(pos, pos + length);
                            continue;
                        }
                    }
                    if (pos + 1 < size && (pos) % length != 0) {
                        if (field.get(pos + 1).getUnit() == null) {
                            field.moveUnit(pos, pos + 1);
                            continue;
                        }
                    }
                }
            }
            else {
                switch (check){
                    case 1: {
                        attack(unit, field.get(pos - 1).getUnit(), field);
                        break;
                    }
                    case 2: {
                        attack(unit, field.get(pos - length).getUnit(), field);
                        break;
                    }
                    case 3: {
                        attack(unit, field.get(pos + 1).getUnit(), field);
                        break;
                    }
                    case 4: {
                        attack(unit, field.get(pos + length).getUnit(), field);
                        break;
                    }
                }
            }
        }
    }
}
