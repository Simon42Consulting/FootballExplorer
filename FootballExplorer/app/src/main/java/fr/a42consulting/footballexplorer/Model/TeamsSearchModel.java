package fr.a42consulting.footballexplorer.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeamsSearchModel {
    public static TeamsSearchModel fromJson(JSONObject jsonObject) {
        TeamsSearchModel league = new TeamsSearchModel();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("teams");

            JSONObject teamJson;
            ArrayList<TeamsSearchTeam> teams = new ArrayList<TeamsSearchTeam>(jsonArray.length());
            // Process each result in json array, decode and convert to team object
            for (int i=0; i < jsonArray.length(); i++) {
                try {
                    teamJson = jsonArray.getJSONObject(i);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

                TeamsSearchTeam team = TeamsSearchTeam.fromJson(teamJson);
                if (team != null) {
                    teams.add(team);
                }
            }

            league.teams = teams;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return league;
    }
    private ArrayList<TeamsSearchTeam> teams = new ArrayList<>();

    public ArrayList<TeamsSearchTeam> getTeams() {
        return teams;
    }
}
