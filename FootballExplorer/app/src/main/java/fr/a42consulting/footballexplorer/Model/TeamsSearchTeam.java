package fr.a42consulting.footballexplorer.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class TeamsSearchTeam {
    public static TeamsSearchTeam fromJson(JSONObject jsonObject) {
        TeamsSearchTeam team = new TeamsSearchTeam();
        try {
            team.id = jsonObject.getString("idTeam");
            team.name = jsonObject.getString("strTeam");
            team.imageUrl = jsonObject.getString("strTeamBadge");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return team;
    }

    private String id;
    private String name;
    private String imageUrl;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
