package gameUtil.Serializers;

import com.google.gson.*;
import gameUtil.Vertex;
import units.UnitType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdjListDeserializer implements JsonDeserializer<ArrayList<ArrayList<Vertex>>> {
    public static UnitType strToType(String s) {
        switch (s) {
            case ("FOOTMAN") :
                return UnitType.FOOTMAN;
            case ("WINGED") :
                return UnitType.WINGED;
            case ("CAVALRY") :
                return UnitType.CAVALRY;
        }
        return null;
    }

    public static void addEdge(int u, int v, ArrayList<ArrayList<Vertex>> adj) {
        adj.get(u).add(new Vertex(v, adj.get(v).get(0).getType()));
        adj.get(v).add(new Vertex(u, adj.get(u).get(0).getType()));
    }

    @Override
    public ArrayList<ArrayList<Vertex>> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ArrayList<ArrayList<Vertex>> adj = new ArrayList<>();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        //String land = adjacent.get("land").toString();
        String s = jsonObject.get("land").toString();
        String land = jsonObject.get("land").toString().substring(1, s.length() - 1);
        List<String> parts = Arrays.asList(land.split(","));
        int size = parts.size() - 1;
        int length = (int) Math.sqrt(size);
        int lastRow = (int) (size - length);

        for (int i = 0; i <= size; i++) {
            adj.add(i, new ArrayList<Vertex>());
            adj.get(i).add(0, new Vertex(i, strToType(parts.get(i))));
        }
        for (int i = 1; i < parts.size() -1; i++) {
            if (i % length == 0 && i <= lastRow) {
                addEdge(i, i + length, adj);
                continue;
            }
            if (i > lastRow && i < size) {
                addEdge(i, i + 1, adj);
                continue;
            }
            addEdge(i, i + 1, adj);
            addEdge(i, i + length, adj);
        }
        return adj;
    }
}
