package gameUtil;

import unitService.ActionService;
import unitService.UnitAction;
import units.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static units.UnitType.*;

public class Field {
    private ArrayList<ArrayList<Vertex>> adj = new ArrayList<ArrayList<Vertex>>();
    protected PlayerList players = new PlayerList();
    private int size;

    public static class Vertex {
        private int index;
        private UnitType type;
        private Unit unit;

        public Vertex(int index, UnitType type) {
            this.index = index;
            this.type = type;
        }

        public int getIndex() {
            return index;
        }

        public UnitType getType() {
            return type;
        }

        public Unit getUnit() {
            return unit;
        }
    }

    public Field(int size, ArrayList<String> names) {
        this.size = size;
        int lastRow = size * size - size;
        int n = size * size;
        for (int i = 0; i <= n; i++) {
            adj.add(i, new ArrayList<Vertex>());
            adj.get(i).add(0, new Vertex(i, randomType()));
        }
        for (int i = 1; i < n; i++) {
            //крайний правый столбец
            if (i % size == 0 && i <= lastRow) {
                addEdge(i, i + size);
                continue;
            }
            //нижняя строчка
            if (i > lastRow && i < n) {
                addEdge(i, i + 1);
                continue;
            }
            //остальные элементы
            addEdge(i, i + 1);
            addEdge(i, i + size);
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
                    setUnit(footman, 2, curPlayer);
                    setUnit(winged, 3, curPlayer);
                    setUnit(cavalry, 4, curPlayer);
                    k++;
                    break;
                }
                case 1 : {
                    setUnit(footman, size * size - 1, curPlayer);
                    setUnit(winged, size * size - 2, curPlayer);
                    setUnit(cavalry, size * size - 3, curPlayer);
                    k++;
                    break;
                }
                case 2 : {
                    setUnit(footman, size * (size - 4) + 1, curPlayer);
                    setUnit(winged, size * (size - 3) + 1, curPlayer);
                    setUnit(cavalry, size * (size - 2) + 1, curPlayer);
                    k++;
                    break;
                }
                case 3 : {
                    setUnit(footman, size * 2, curPlayer);
                    setUnit(winged, size * 3, curPlayer);
                    setUnit(cavalry, size * 4, curPlayer);
                    k = 0;
                    break;
                }
            }
        }

    }

    public Vertex get(int index) {
        return adj.get(index).get(0);
    }

    public UnitType getType(int i) {
        return adj.get(i).get(0).getType();
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(new Vertex(v, adj.get(v).get(0).getType()));
        adj.get(v).add(new Vertex(u, adj.get(u).get(0).getType()));
    }

    public void addVertex(UnitType ut) {
        adj.add(new ArrayList<>());
        adj.get(adj.size() - 1).add(new Vertex(adj.size() - 1, ut));
    }

    public int getSize() {
        return size;
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

    private void setUnit(Unit unit, int position, Player player) {
        adj.get(position).get(0).unit = unit;
        player.getArmy().add(unit);
        unit.setPosition(position);
    }

    public void moveUnit(int s, int f) {
        adj.get(f).get(0).unit = adj.get(s).get(0).unit;
        adj.get(s).get(0).unit = null;
        adj.get(f).get(0).unit.setPosition(f);
    }

    public void loseUnit(Player p, Unit unit) {
        p.getArmy().remove(unit);
        adj.get(unit.getPosition()).get(0).unit = null;
    }

    public boolean checkEnd() {
        for (int i = 0; i < players.getSize(); i++) {
            if (players.get(i).getArmy().size() == 0)
                return false;
        }
        return true;
    }

    public void printLoser() {
        for (int i = 0; i < players.getSize(); i++) {
            if (players.get(i).getArmy().size() == 0)
                System.out.println(players.get(i).getName() + " has been eliminated!!!");
        }
    }

    void printField() {
        for (int i = 1; i <= size * size; i++) {
            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == FOOTMAN && adj.get(i).get(0).unit.getFaction() == Faction.RED) {
                System.out.print(" |r f| ");
            }

            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == CAVALRY && adj.get(i).get(0).unit.getFaction() == Faction.RED) {
                System.out.print(" |r c| ");
            }

            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == WINGED && adj.get(i).get(0).unit.getFaction() == Faction.RED) {
                System.out.print(" |r w| ");
            }

            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == FOOTMAN && adj.get(i).get(0).unit.getFaction() == Faction.BLUE) {
                System.out.print(" |b f| ");
            }

            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == CAVALRY && adj.get(i).get(0).unit.getFaction() == Faction.BLUE) {
                System.out.print(" |b c| ");
            }

            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == WINGED && adj.get(i).get(0).unit.getFaction() == Faction.BLUE) {
                System.out.print(" |b w| ");
            }

            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == FOOTMAN && adj.get(i).get(0).unit.getFaction() == Faction.GREEN) {
                System.out.print(" |g f| ");
            }

            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == CAVALRY && adj.get(i).get(0).unit.getFaction() == Faction.GREEN) {
                System.out.print(" |g c| ");
            }

            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == WINGED && adj.get(i).get(0).unit.getFaction() == Faction.GREEN) {
                System.out.print(" |g w| ");
            }

            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == FOOTMAN && adj.get(i).get(0).unit.getFaction() == Faction.PURPLE) {
                System.out.print(" |p f| ");
            }

            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == CAVALRY && adj.get(i).get(0).unit.getFaction() == Faction.PURPLE) {
                System.out.print(" |p c| ");
            }

            if (adj.get(i).get(0).unit != null && adj.get(i).get(0).unit.getType() == WINGED && adj.get(i).get(0).unit.getFaction() == Faction.PURPLE) {
                System.out.print(" |p w| ");
            }

            if (adj.get(i).get(0).unit == null && adj.get(i).get(0).getType() == FOOTMAN) {
                System.out.print(" |≈≈≈| ");
            }

            if (adj.get(i).get(0).unit == null && adj.get(i).get(0).getType() == CAVALRY) {
                System.out.print(" |___| ");
            }

            if (adj.get(i).get(0).unit == null && adj.get(i).get(0).getType() == WINGED) {
                System.out.print(" |_А_| ");
            }
            if (i % size == 0) {
                System.out.println();
            }
        }
        System.out.println("=====================================================");
    }
}