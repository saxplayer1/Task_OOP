package gameUtil;

import unitService.ActionService;
import units.Unit;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game {
    private ArrayList<String> names = new ArrayList<>();
    private Field field;

    public Game(int size, ArrayList<String> names) {
        this.names = names;
        this.field = new Field(size, names);
    }

    public void startGame() throws InterruptedException {
        if(this.field.getSize() < 5) {
            System.out.println("Size of the field is to small");
            return;
        }
        field.printField();
        Player curPlayer = field.players.next();
        ActionService action = new ActionService(field);
        double turnCount = 0;

        while (field.checkEnd()) {
            for(Unit unit : curPlayer.getArmy()) {
                action.move(unit, field);
            }
            System.out.println("this is " + curPlayer.getName());
            field.printField();
            curPlayer = field.players.next();
            TimeUnit.SECONDS.sleep(0);
            turnCount += 0.25;
            if (turnCount == 30) {
                break;
            }
        }
        if ((turnCount == 30)) {
            System.out.println("Draw!!!");
        } else {
            field.printLoser();
        }
    }
}
