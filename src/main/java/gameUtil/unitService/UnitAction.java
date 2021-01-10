package gameUtil.unitService;

import gameUtil.Field;
import gameUtil.FieldService;
import units.Unit;

public interface UnitAction {
    default void attack(Unit Attaker, Unit enemy, Field field) {
        FieldService fs = new FieldService();
        enemy.setHp(enemy.getHp() - Attaker.getAtk());
        if (enemy.getHp() <= 0) {
            fs.loseUnit(enemy.getOwner(), enemy, field);
        }
    }

    default int checkEnemy(Unit unit, int position, Field field) {
        FieldService fs = new FieldService();
        if (position - 1 > 0)
            if (fs.get(position - 1, field).getUnit() != null && fs.get(position - 1, field).getUnit().getOwner() != unit.getOwner())
                return 1;

        if (position - fs.getSize(field) > 0)
            if (fs.get(position - fs.getSize(field), field).getUnit() != null && fs.get(position - fs.getSize(field), field).getUnit().getOwner() != unit.getOwner())
                return 2;

        if (position + 1 < fs.getSize(field) * fs.getSize(field))
            if(fs.get(position + 1, field).getUnit() != null && fs.get(position + 1, field).getUnit().getOwner() != unit.getOwner())
                return 3;

        if (position + fs.getSize(field) < fs.getSize(field) * fs.getSize(field))
            if (fs.get(position + fs.getSize(field), field).getUnit() != null && fs.get(position + fs.getSize(field), field).getUnit().getOwner() != unit.getOwner())
                return 4;

        return 0;
    }

    void move(Unit unit, int mp, Field field);
}
