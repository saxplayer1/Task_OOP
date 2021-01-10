package gameUtil;

import gameUtil.Player;

public class PlayerList {
    private Node start;
    private int size = 0;
    private Node curPlayer;

    public int getSize() {
        return size;
    }

    public Node getStart() {
        return start;
    }

    public PlayerList(Player player) {
        this.start = new Node(player, null, null);
        start.next = start;
        start.prev = start;

    }

    public void clear() {
        this.start = new Node(null, null, null);
        start.next = start;
        start.prev = start;
        size = 0;
    }

    public PlayerList() {
    }

    public void add(Player player) {
        if (start == null) {
            start = new Node(player);
            start.next = start;
            start.prev = start;
            curPlayer = start;
            size++;
            return;
        }
        Node nPlayer = new Node(player, start, start.prev);
        start.prev.next = nPlayer;
        start.prev = nPlayer;
        size++;
    }

    protected void remove() {
        start.prev = start.prev.prev;
        start.prev.prev.next = start;
        size--;
    }

    protected Player get(Player p) {
        Node cur = start;
        while (cur.player != p) {
            cur = cur.next;
        }
        return cur.player;
    }

    public Player get(int index) {
        Node cur = start;
        int i = 0;
        while (index != i) {
            cur = cur.next;
            i++;
        }
        return cur.player;
    }
    protected Player next() {
        Player tmp = curPlayer.player;
        curPlayer = curPlayer.next;
        return tmp;
    }

    private class Node {
        private Player player;
        private Node next;
        private Node prev;

        public Node(Player player) {
            this.player = player;
        }

        public Node(Player player, Node next, Node prev) {
            this.player = player;
            this.next = next;
            this.prev = prev;
        }
    }
}
