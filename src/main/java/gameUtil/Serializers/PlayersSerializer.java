package gameUtil.Serializers;

import com.google.gson.*;
import gameUtil.Player;
import gameUtil.PlayerList;
import units.Unit;

import java.lang.reflect.Type;

public class PlayersSerializer implements JsonSerializer<PlayerList> {
    @Override
    public JsonElement serialize(PlayerList src, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject players = new JsonObject();
        for (int i = 0; i < src.getSize(); i++) {
            JsonObject player = new JsonObject();
            player.add("name", new JsonPrimitive(src.get(i).getName()));
            player.add("faction", new JsonPrimitive(src.get(i).factionToString()));
            int k = 1;
            JsonObject army = new JsonObject();
            for (Unit soldier : src.get(i).getArmy()) {
                JsonObject s = new JsonObject();
                s.add("type", new JsonPrimitive(soldier.typeToString()));
                s.add("hp", new JsonPrimitive(Integer.toString(soldier.getHp())));
                s.add("atk", new JsonPrimitive(Integer.toString(soldier.getAtk())));
                s.add("position", new JsonPrimitive(Integer.toString(soldier.getPosition())));
                army.add("soldier " + k, s);
                k++;
            }
            player.add("army", army);
            players.add("player " + i, player);
        }
        return players;
    }
}
