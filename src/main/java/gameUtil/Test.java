package gameUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gameUtil.Serializers.AdjListDeserializer;
import gameUtil.Serializers.AdjListSerializer;
import gameUtil.Serializers.PlayersDeserializer;
import gameUtil.Serializers.PlayersSerializer;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        FieldService fs = new FieldService();
        ArrayList<String> names = new ArrayList<>();
        names.add("denis");
        names.add("vitalik");
        Field field = fs.createField(6, names);



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

        Field field1 = fs.createField(6, names);
        field1.players.clear();
        field1.adj.clear();
        field1 = gson.fromJson(json, field.getClass());
        System.out.println(json);
    }
}
