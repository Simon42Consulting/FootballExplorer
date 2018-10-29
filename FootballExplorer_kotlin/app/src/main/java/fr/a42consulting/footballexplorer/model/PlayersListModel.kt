package fr.a42consulting.footballexplorer.model

import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

class PlayersListModel {
    var players = ArrayList<PlayersListPlayer>()
        private set

    companion object {
        fun fromJson(jsonObject: JSONObject): PlayersListModel {
            val team = PlayersListModel()
            try {
                val jsonArray = jsonObject.getJSONArray("player")

                var playerJson: JSONObject
                val players = ArrayList<PlayersListPlayer>(jsonArray.length())
                // Process each result in json array, decode and convert to player object
                for (i in 0 until jsonArray.length()) {
                    try {
                        playerJson = jsonArray.getJSONObject(i)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        continue
                    }

                    val player = PlayersListPlayer.fromJson(playerJson)
                    if (player != null) {
                        players.add(player)
                    }
                }

                team.players = players

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return team
        }
    }
}
