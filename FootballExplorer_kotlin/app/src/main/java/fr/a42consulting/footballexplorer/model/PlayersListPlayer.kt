package fr.a42consulting.footballexplorer.model

import org.json.JSONException
import org.json.JSONObject

class PlayersListPlayer {

    var id: String? = null
        private set
    var name: String? = null
        private set
    var imageUrl: String? = null
        private set
    var role: String? = null
        private set
    var price: String? = null
        private set
    var birth: String? = null
        private set

    companion object {
        fun fromJson(jsonObject: JSONObject): PlayersListPlayer? {
            val player = PlayersListPlayer()
            try {
                player.id = jsonObject.getString("idPlayer")
                player.name = jsonObject.getString("strPlayer")
                player.imageUrl = jsonObject.getString("strCutout")
                if (!jsonObject.isNull("strPosition")) {
                    player.role = jsonObject.getString("strPosition")
                }
                if (!jsonObject.isNull("strSigning")) {
                    player.price = jsonObject.getString("strSigning")
                }
                player.birth = jsonObject.getString("dateBorn")
            } catch (e: JSONException) {
                e.printStackTrace()
                return null
            }

            return player
        }
    }
}
