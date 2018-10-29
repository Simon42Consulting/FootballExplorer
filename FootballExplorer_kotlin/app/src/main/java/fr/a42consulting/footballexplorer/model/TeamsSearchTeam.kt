package fr.a42consulting.footballexplorer.model

import org.json.JSONException
import org.json.JSONObject

class TeamsSearchTeam {

    lateinit var id: String
        private set
    lateinit var name: String
        private set
    lateinit var imageUrl: String
        private set

    companion object {
        fun fromJson(jsonObject: JSONObject): TeamsSearchTeam? {
            val team = TeamsSearchTeam()
            try {
                team.id = jsonObject.getString("idTeam")
                team.name = jsonObject.getString("strTeam")
                team.imageUrl = jsonObject.getString("strTeamBadge")
            } catch (e: JSONException) {
                e.printStackTrace()
                return null
            }

            return team
        }
    }
}
