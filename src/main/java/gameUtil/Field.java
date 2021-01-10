package gameUtil;

import java.util.ArrayList;

public class Field {
    protected ArrayList<ArrayList<Vertex>> adj = new ArrayList<ArrayList<Vertex>>();
    protected PlayerList players = new PlayerList();
    protected int size;

    public Field(ArrayList<ArrayList<Vertex>> adj, PlayerList players, int size) {
        this.adj = adj;
        this.players = players;
        this.size = size;
    }

    public Field() {
    }
}