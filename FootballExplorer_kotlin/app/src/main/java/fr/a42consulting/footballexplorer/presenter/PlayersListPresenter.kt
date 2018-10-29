package fr.a42consulting.footballexplorer.presenter

import org.json.JSONException
import org.json.JSONObject

import fr.a42consulting.footballexplorer.model.PlayersListModel
import fr.a42consulting.footballexplorer.view.IPlayersListView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import java.net.URLEncoder

class PlayersListPresenter(private var playersListView: IPlayersListView?) : IPlayersListPresenter {
    private var team: PlayersListModel? = null

    override fun onLoadPlayers(request: String) {
        doAsync {
            val result = URL("https://www.thesportsdb.com/api/v1/json/1/searchplayers.php?t=" + URLEncoder.encode(request, "UTF-8")).readText()
            uiThread {
                try {
                    val jsonResult = JSONObject(result)
                    team = PlayersListModel.fromJson(jsonResult)
                    playersListView!!.onRequestResult(team!!.players)
                } catch (e: JSONException) {
                    playersListView!!.onMessage("La récupération des données a échoué.")
                    e.printStackTrace()
                }
            }
        }
    }

    override fun resetView(view: IPlayersListView?) {
        playersListView = view
    }
}
