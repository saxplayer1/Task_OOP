package unitService;

import gameUtil.Field;
import units.Unit;
import units.UnitType;

import java.util.HashMap;
import java.util.Map;

public class ActionService {
    private Map<UnitType, UnitAction> action = new HashMap<>();
    private Field field;

    public ActionService(Field field) {
        action.put(UnitType.FOOTMAN, new FootmanAction());
        action.put(UnitType.CAVALRY, new CavalryAction());
        action.put(UnitType.WINGED, new WingedAction());
        this.field = field;
    }

    public void attack(Unit attacker, Unit enemy) {
        action.get(attacker.getType()).attack(enemy, attacker, field);
    }

    public void move(Unit unit, Field field) {
        action.get(unit.getType()).move(unit, unit.getActionPoints(), field);
    }
}
