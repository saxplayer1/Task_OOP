package gameUtil.Serializers;

import com.google.gson.*;
import gameUtil.Vertex;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AdjListSerializer implements JsonSerializer<ArrayList<ArrayList<Vertex>>> {
    @Override
    public JsonElement serialize(ArrayList<ArrayList<Vertex>> src, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        JsonArray land = new JsonArray();
        result.add("land", land);
        for (ArrayList<Vertex> list: src){
                land.add(
                        new JsonPrimitive(list.get(0).typeToString()));
        }
        return result;
    }
}
