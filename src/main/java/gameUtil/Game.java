package gameUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gameUtil.Serializers.AdjListDeserializer;
import gameUtil.Serializers.AdjListSerializer;
import gameUtil.Serializers.PlayersDeserializer;
import gameUtil.Serializers.PlayersSerializer;
import gameUtil.unitService.ActionService;
import units.Unit;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game {
    private ArrayList<String> names = new ArrayList<>();
    private Field field;
    private FieldService fs = new FieldService();

    public Game(int size, ArrayList<String> names) {
        this.names = names;
        this.field = fs.createField(size, names);
    }

    public void startGame() throws InterruptedException {
        if(this.fs.getSize(field) < 5) {
            System.out.println("Size of the field is to small");
            return;
        }
        if(this.names.size() > 4) {
            System.out.println("Too many players");
        }
        //field.printField();
        Player curPlayer = field.players.next();
        ActionService action = new ActionService(field);
        double turnCount = 0;

        while (fs.checkEnd(field)) {
            if (turnCount == 15) {
                field = fs.demonstrateSerialization(field);
            }
            for(Unit unit : curPlayer.getArmy()) {
                action.move(unit, field);
            }
            System.out.println("this is " + curPlayer.getName());
            //field.printField();
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
            fs.printLoser(field);
        }
    }
}
