package fr.a42consulting.footballexplorer.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlayersListModel {
    public static PlayersListModel fromJson(JSONObject jsonObject) {
        PlayersListModel team = new PlayersListModel();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("player");

            JSONObject playerJson;
            ArrayList<PlayersListPlayer> players = new ArrayList<>(jsonArray.length());
            // Process each result in json array, decode and convert to player object
            for (int i=0; i < jsonArray.length(); i++) {
                try {
                    playerJson = jsonArray.getJSONObject(i);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

                PlayersListPlayer player = PlayersListPlayer.fromJson(playerJson);
                if (player != null) {
                    players.add(player);
                }
            }

            team.players = players;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return team;
    }
    private ArrayList<PlayersListPlayer> players = new ArrayList<>();

    public ArrayList<PlayersListPlayer> getPlayers() {
        return players;
    }
}
