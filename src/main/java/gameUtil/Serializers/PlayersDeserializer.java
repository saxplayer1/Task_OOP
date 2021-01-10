package gameUtil.Serializers;

import com.google.gson.*;
import gameUtil.Faction;
import gameUtil.Player;
import gameUtil.PlayerList;
import units.*;

import java.lang.reflect.Type;

public class PlayersDeserializer implements JsonDeserializer<PlayerList> {

    private static Faction strToFaction(String s) {
        switch (s) {
            case ("RED") :
                return Faction.RED;
            case ("BLUE") :
                return Faction.BLUE;
            case ("GREEN") :
                return Faction.GREEN;
            case ("PURPLE") :
                return Faction.PURPLE;
        }
        return null;
    }

    public UnitType strToType(String s){
        switch (s){
            case "WINGED":
                return UnitType.WINGED;
            case "CAVALRY":
                return UnitType.CAVALRY;
            case "FOOTMAN":
                return UnitType.FOOTMAN;
        }
        return null;
    }

    @Override
    public PlayerList deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        PlayerList playerList = new PlayerList();
        for (int i = 0; i < jsonObject.size(); i++) {
            JsonObject p = jsonObject.get("player " + i).getAsJsonObject();
            Player player = new Player(strToFaction(p.get("faction").getAsString()), p.get("name").getAsString());
            JsonObject army = p.getAsJsonObject("army");
            for (int j = 1; j <= army.size(); j++) {
               JsonObject soldier = army.getAsJsonObject("soldier " + j);
               //JsonObject unitType = soldier.getAsJsonObject("type");
               switch (soldier.get("type").getAsString()) {
                   case ("FOOTMAN") : {
                       player.getArmy().add(new Footman(player));
                       player.getArmy().get(j - 1).setHp(soldier.get("hp").getAsInt());
                       player.getArmy().get(j - 1).setPosition(soldier.get("position").getAsInt());
                       break;
                   }
                   case ("WINGED") : {
                       player.getArmy().add(new Winged(player));
                       player.getArmy().get(j - 1).setHp(soldier.get("hp").getAsInt());
                       player.getArmy().get(j - 1).setPosition(soldier.get("position").getAsInt());
                       break;
                   }
                   case ("CAVALRY") : {
                       player.getArmy().add(new Cavalry(player));
                       player.getArmy().get(j - 1).setHp(soldier.get("hp").getAsInt());
                       player.getArmy().get(j - 1).setPosition(soldier.get("position").getAsInt());
                       break;
                   }
               }
            }
            playerList.add(player);
        }
//        playerList.add(new Player(
//                strToFaction(jsonObject.get("faction").toString()),
//                jsonObject.
//        ));
        return playerList;
    }
}
