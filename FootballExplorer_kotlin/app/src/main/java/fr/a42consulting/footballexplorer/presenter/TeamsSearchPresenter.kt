package fr.a42consulting.footballexplorer.presenter

import org.json.JSONException
import org.json.JSONObject

import fr.a42consulting.footballexplorer.model.TeamsSearchModel
import fr.a42consulting.footballexplorer.view.ITeamsSearchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import java.net.URLEncoder

class TeamsSearchPresenter(private var searchView: ITeamsSearchView?) : ITeamsSearchPresenter {
    private var league: TeamsSearchModel? = null

    override fun onSearch(request: String) {
        doAsync {
            val result = URL("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=" + URLEncoder.encode(request, "UTF-8")).readText()
            uiThread {
                try {
                    val jsonResult = JSONObject(result)
                    league = TeamsSearchModel.fromJson(jsonResult)
                    searchView!!.onSearchResult(league!!.teams, true)
                } catch (e: JSONException) {
                    searchView!!.onMessage("La récupération des données a échoué.")
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onSelectTeam(position: Int) {
        val teamName = league!!.teams[position].name
        searchView!!.onShowTeam(teamName)
    }

    override fun resetView(view: ITeamsSearchView?) {
        searchView = view
        if (league != null && searchView != null) {
            searchView!!.onSearchResult(league!!.teams, false)
        }
    }
}
