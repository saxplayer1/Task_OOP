package gameUtil.unitService;

import gameUtil.Faction;
import gameUtil.Field;
import gameUtil.FieldService;
import units.Unit;
import units.UnitType;

public class FootmanAction implements UnitAction {
    @Override
    public void move(Unit unit, int mp, Field field) {
        FieldService fs = new FieldService();
        int size = fs.getSize(field) * fs.getSize(field);
        int length = fs.getSize(field);
        for (int i = 0; i < mp; i++) {
            int pos = unit.getPosition();
            int check = checkEnemy(unit, pos, field);
            if (check == 0) {
                if (unit.getFaction() == Faction.RED) {
                    if (pos + length < size) {
                    if (fs.get(pos + length, field).getUnit() == null && fs.get(pos + length, field).getType() != UnitType.WINGED) {
                        fs.moveUnit(pos, pos + length, field);
                        continue;
                    }
                }
                    if (pos - 1 > 0 && (pos - 1) % length != 0) {
                        if (fs.get(pos - 1, field).getUnit() == null && fs.get(pos - 1, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos - 1, field);
                            continue;
                        }
                    }
                    if (pos + 1 < size && (pos) % length != 0) {
                        if (fs.get(pos + 1, field).getUnit() == null && fs.get(pos + 1, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos + 1, field);
                            continue;
                        }
                    }
                    if (pos - length > 0) {
                        if (fs.get(pos - length, field).getUnit() == null && fs.get(pos - length, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos - length, field);
                            continue;
                        }
                    }
                }


                if (unit.getFaction() == Faction.BLUE) {
                    if (pos - length > 0) {
                        if (fs.get(pos - length, field).getUnit() == null && fs.get(pos - length, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos - length, field);
                            continue;
                        }
                    }
                    if (pos - 1 > 0 && (pos - 1) % length != 0) {
                        if (fs.get(pos - 1, field).getUnit() == null && fs.get(pos - 1, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos - 1, field);
                            continue;
                        }
                    }
                    if (pos + 1 < size && (pos) % length != 0) {
                        if (fs.get(pos + 1, field).getUnit() == null && fs.get(pos + 1, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos + 1, field);
                            continue;
                        }
                    }
                    if (pos + length < size) {
                        if (fs.get(pos + length, field).getUnit() == null && fs.get(pos + length, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos + length, field);
                            continue;
                        }
                    }
                }

                if (unit.getFaction() == Faction.GREEN) {
                    if (pos + 1 < size && (pos) % length != 0) {
                        if (fs.get(pos + 1, field).getUnit() == null && fs.get(pos + 1, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos + 1, field);
                            continue;
                        }
                    }
                    if (pos - length > 0) {
                        if (fs.get(pos - length, field).getUnit() == null && fs.get(pos - length, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos - length, field);
                            continue;
                        }
                    }
                    if (pos + length < size) {
                        if (fs.get(pos + length, field).getUnit() == null && fs.get(pos + length, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos + length, field);
                            continue;
                        }
                    }
                    if (pos - 1 > 0 && (pos - 1) % length != 0) {
                        if (fs.get(pos - 1, field).getUnit() == null && fs.get(pos - 1, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos - 1, field);
                            continue;
                        }
                    }
                }

                if (unit.getFaction() == Faction.PURPLE) {
                    if (pos - 1 > 0 && (pos - 1) % length != 0) {
                        if (fs.get(pos - 1, field).getUnit() == null && fs.get(pos - 1, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos - 1, field);
                            continue;
                        }
                    }
                    if (pos - length > 0) {
                        if (fs.get(pos - length, field).getUnit() == null && fs.get(pos - length, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos - length, field);
                            continue;
                        }
                    }
                    if (pos + length < size) {
                        if (fs.get(pos + length, field).getUnit() == null && fs.get(pos + length, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos + length, field);
                            continue;
                        }
                    }
                    if (pos + 1 < size && (pos) % length != 0) {
                        if (fs.get(pos + 1, field).getUnit() == null && fs.get(pos + 1, field).getType() != UnitType.WINGED) {
                            fs.moveUnit(pos, pos + 1, field);
                            continue;
                        }
                    }
                }
            }
            else {
                switch (check){
                    case 1: {
                        attack(unit, fs.get(pos - 1, field).getUnit(), field);
                        break;
                    }
                    case 2: {
                        attack(unit, fs.get(pos - length, field).getUnit(), field);
                        break;
                    }
                    case 3: {
                        attack(unit, fs.get(pos + 1, field).getUnit(), field);
                        break;
                    }
                    case 4: {
                        attack(unit, fs.get(pos + length, field).getUnit(), field);
                        break;
                    }
                }
            }
        }
    }
}
