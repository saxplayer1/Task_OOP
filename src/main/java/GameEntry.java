import gameUtil.Game;

import java.util.ArrayList;

public class GameEntry {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> names = new ArrayList<>();
        names.add("denis");
        names.add("vitalik");
        names.add("artyom");
        names.add("vladimir");
        Game g = new Game(8, names);
        g.startGame();
    }
}
