package unitService;

import gameUtil.Field;
import units.Unit;

public interface UnitAction {
    default void attack(Unit Attaker, Unit enemy, Field field) {
        enemy.setHp(enemy.getHp() - Attaker.getAtk());
        if (enemy.getHp() <= 0) {
            field.loseUnit(enemy.getOwner(), enemy);
        }
    }

    default int checkEnemy(Unit unit, int position, Field field) {
        if (position - 1 > 0)
            if (field.get(position - 1).getUnit() != null && field.get(position - 1).getUnit().getOwner() != unit.getOwner())
                return 1;

        if (position - field.getSize() > 0)
            if (field.get(position - field.getSize()).getUnit() != null && field.get(position - field.getSize()).getUnit().getOwner() != unit.getOwner())
                return 2;

        if (position + 1 < field.getSize() * field.getSize())
            if(field.get(position + 1).getUnit() != null && field.get(position + 1).getUnit().getOwner() != unit.getOwner())
                return 3;

        if (position + field.getSize() < field.getSize() * field.getSize())
            if (field.get(position + field.getSize()).getUnit() != null && field.get(position + field.getSize()).getUnit().getOwner() != unit.getOwner())
                return 4;

        return 0;
    }

    void move(Unit unit, int mp, Field field);
}
