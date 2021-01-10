package gameUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gameUtil.Serializers.AdjListDeserializer;
import gameUtil.Serializers.AdjListSerializer;
import gameUtil.Serializers.PlayersDeserializer;
import gameUtil.Serializers.PlayersSerializer;
import units.*;

import java.util.ArrayList;
import java.util.Random;

import static units.UnitType.*;

public class FieldService {
    public Field createField(){
        return new Field();
    }

    public Field createField(int size, ArrayList<String> names) {
        int lastRow = size * size - size;
        int n = size * size;
        ArrayList<ArrayList<Vertex>> adj = new ArrayList<ArrayList<Vertex>>();
        PlayerList players = new PlayerList();

        for (int i = 0; i <= n; i++) {
            adj.add(i, new ArrayList<Vertex>());
            adj.get(i).add(0, new Vertex(i, randomType()));
        }
        for (int i = 1; i < n; i++) {
            if (i % size == 0 && i <= lastRow) {
                addEdge(i, i + size, adj);
                continue;
            }
            if (i > lastRow && i < n) {
                addEdge(i, i + 1, adj);
                continue;
            }
            addEdge(i, i + 1, adj);
            addEdge(i, i + size, adj);
        }

        Faction[] f = new Faction[4];
        f[0] = Faction.RED;
        f[1] = Faction.BLUE;
        f[2] = Faction.GREEN;
        f[3] = Faction.PURPLE;

        int j = 0;
        for (String name : names) {
            players.add(new Player(f[j], name));
            j++;
        }

        int k = 0;
        for (int i = 0; i < players.getSize(); i++) {
            Player curPlayer = players.get(i);

            Unit footman = new Footman(curPlayer);
            Unit winged = new Winged(curPlayer);
            Unit cavalry = new Cavalry(curPlayer);

            switch (k) {
                case 0 : {
                    setUnit(footman, 2, curPlayer, adj);
                    setUnit(winged, 3, curPlayer, adj);
                    setUnit(cavalry, 4, curPlayer, adj);
                    k++;
                    break;
                }
                case 1 : {
                    setUnit(footman, size * size - 1, curPlayer, adj);
                    setUnit(winged, size * size - 2, curPlayer, adj);
                    setUnit(cavalry, size * size - 3, curPlayer, adj);
                    k++;
                    break;
                }
                case 2 : {
                    setUnit(footman, size * (size - 4) + 1, curPlayer, adj);
                    setUnit(winged, size * (size - 3) + 1, curPlayer, adj);
                    setUnit(cavalry, size * (size - 2) + 1, curPlayer, adj);
                    k++;
                    break;
                }
                case 3 : {
                    setUnit(footman, size * 2, curPlayer, adj);
                    setUnit(winged, size * 3, curPlayer, adj);
                    setUnit(cavalry, size * 4, curPlayer, adj);
                    k = 0;
                    break;
                }
            }
        }
        return new Field(adj, players, size);
    }


    public Vertex get(int index, Field field) {
        return field.adj.get(index).get(0);
    }

    public UnitType getType(int i, Field field) {
        return field.adj.get(i).get(0).getType();
    }

    public void addEdge(int u, int v, ArrayList<ArrayList<Vertex>> adj) {
        adj.get(u).add(new Vertex(v, adj.get(v).get(0).getType()));
        adj.get(v).add(new Vertex(u, adj.get(u).get(0).getType()));
    }

    public void addVertex(UnitType ut, Field field) {
        field.adj.add(new ArrayList<>());
        field.adj.get(field.adj.size() - 1).add(new Vertex(field.adj.size() - 1, ut));
    }

    public int getSize(Field field) {
        return field.size;
    }

    private static UnitType randomType() {
        Random random = new Random();
        int r = random.nextInt(3);
        switch (r) {
            case 0 :
                return FOOTMAN;
            case 1 :
                return WINGED;
            case 2 :
                return CAVALRY;
        }
        return null;
    }

    private void setUnit(Unit unit, int position, Player player, ArrayList<ArrayList<Vertex>> adj) {
        adj.get(position).get(0).setUnit(unit);
        player.getArmy().add(unit);
        unit.setPosition(position);
    }

    public void moveUnit(int s, int f, Field field) {
        field.adj.get(f).get(0).setUnit(field.adj.get(s).get(0).getUnit());
        field.adj.get(s).get(0).setUnit(null);
        field.adj.get(f).get(0).getUnit().setPosition(f);
    }

    public void loseUnit(Player p, Unit unit, Field field) {
        p.getArmy().remove(unit);
        field.adj.get(unit.getPosition()).get(0).setUnit(null);
    }

    public boolean checkEnd(Field field) {
        for (int i = 0; i < field.players.getSize(); i++) {
            if (field.players.get(i).getArmy().size() == 0)
                return false;
        }
        return true;
    }

    public void printLoser(Field field) {
        for (int i = 0; i < field.players.getSize(); i++) {
            if (field.players.get(i).getArmy().size() == 0)
                System.out.println(field.players.get(i).getName() + " has been eliminated!!!");
        }
    }

    public Field demonstrateSerialization(Field field) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(field.adj.getClass(), new AdjListSerializer())
                .registerTypeAdapter(field.players.getClass(), new PlayersSerializer())
                .create();
        String json = gson.toJson(field);

        gson = new GsonBuilder()
                .registerTypeAdapter(field.adj.getClass(), new AdjListDeserializer())
                .registerTypeAdapter(field.players.getClass(), new PlayersDeserializer())
                .create();

        Field fieldDemo;
        fieldDemo = gson.fromJson(json, field.getClass());

        for (int i = 0; i < fieldDemo.players.getSize(); i++) {
            for (Unit unit : fieldDemo.players.get(i).getArmy()) {
                fieldDemo.adj.get(unit.getPosition()).get(0).setUnit(unit);
            }
        }

        System.out.println(json);
        return fieldDemo;
    }

//    void printField() {
//        for (int i = 1; i <= size * size; i++) {
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == FOOTMAN && adj.get(i).get(0).unit.getFaction() == Faction.RED) {
//                System.out.print("|r f|");
//            }
//
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == CAVALRY && adj.get(i).get(0).unit.getFaction() == Faction.RED) {
//                System.out.print("|r c|");
//            }
//
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == WINGED && adj.get(i).get(0).unit.getFaction() == Faction.RED) {
//                System.out.print("|r w|");
//            }
//
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == FOOTMAN && adj.get(i).get(0).unit.getFaction() == Faction.BLUE) {
//                System.out.print("|b f|");
//            }
//
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == CAVALRY && adj.get(i).get(0).unit.getFaction() == Faction.BLUE) {
//                System.out.print("|b c|");
//            }
//
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == WINGED && adj.get(i).get(0).unit.getFaction() == Faction.BLUE) {
//                System.out.print("|b w|");
//            }
//
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == FOOTMAN && adj.get(i).get(0).unit.getFaction() == Faction.GREEN) {
//                System.out.print("|g f|");
//            }
//
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == CAVALRY && adj.get(i).get(0).unit.getFaction() == Faction.GREEN) {
//                System.out.print("|g c|");
//            }
//
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == WINGED && adj.get(i).get(0).unit.getFaction() == Faction.GREEN) {
//                System.out.print("|g w|");
//            }
//
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == FOOTMAN && adj.get(i).get(0).unit.getFaction() == Faction.PURPLE) {
//                System.out.print("|p f|");
//            }
//
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == CAVALRY && adj.get(i).get(0).unit.getFaction() == Faction.PURPLE) {
//                System.out.print("|p c|");
//            }
//
//            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == WINGED && adj.get(i).get(0).unit.getFaction() == Faction.PURPLE) {
//                System.out.print("|p w|");
//            }
//
//            if (adj.get(i).get(0).unit == null && adj.get(i).get(0).getType() == FOOTMAN) {
//                System.out.print("|≈≈≈|");
//            }
//
//            if (adj.get(i).get(0).unit == null && adj.get(i).get(0).getType() == CAVALRY) {
//                System.out.print("|___|");
//            }
//
//            if (adj.get(i).get(0).unit == null && adj.get(i).get(0).getType() == WINGED) {
//                System.out.print("|_А_|");
//            }
//            if (i % size == 0) {
//                System.out.println();
//            }
//        }
//        System.out.println("=====================================================");
//    }
}
