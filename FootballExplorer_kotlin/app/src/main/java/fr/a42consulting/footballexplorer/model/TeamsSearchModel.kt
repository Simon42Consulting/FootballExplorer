package fr.a42consulting.footballexplorer.model

import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

class TeamsSearchModel {
    var teams = ArrayList<TeamsSearchTeam>()
        private set

    companion object {
        fun fromJson(jsonObject: JSONObject): TeamsSearchModel {
            val league = TeamsSearchModel()
            try {
                val jsonArray = jsonObject.getJSONArray("teams")

                var teamJson: JSONObject
                val teams = ArrayList<TeamsSearchTeam>(jsonArray.length())
                // Process each result in json array, decode and convert to team object
                for (i in 0 until jsonArray.length()) {
                    try {
                        teamJson = jsonArray.getJSONObject(i)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        continue
                    }

                    val team = TeamsSearchTeam.fromJson(teamJson)
                    if (team != null) {
                        teams.add(team)
                    }
                }

                league.teams = teams

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return league
        }
    }
}
