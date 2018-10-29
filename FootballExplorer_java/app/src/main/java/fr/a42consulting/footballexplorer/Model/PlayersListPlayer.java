package fr.a42consulting.footballexplorer.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class PlayersListPlayer {
    public static PlayersListPlayer fromJson(JSONObject jsonObject) {
        PlayersListPlayer player = new PlayersListPlayer();
        try {
            player.id = jsonObject.getString("idPlayer");
            player.name = jsonObject.getString("strPlayer");
            player.imageUrl = jsonObject.getString("strCutout");
            if (!jsonObject.isNull("strPosition")) {
                player.role = jsonObject.getString("strPosition");
            }
            if (!jsonObject.isNull("strSigning")) {
                player.price = jsonObject.getString("strSigning");
            }
            player.birth = jsonObject.getString("dateBorn");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return player;
    }

    private String id;
    private String name;
    private String imageUrl;
    private String role;
    private String price;
    private String birth;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRole() {
        return role;
    }

    public String getPrice() {
        return price;
    }

    public String getBirth() {
        return birth;
    }
}
