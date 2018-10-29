package fr.a42consulting.footballexplorer.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.SearchView
import android.widget.Toast

import java.util.ArrayList

import fr.a42consulting.footballexplorer.model.TeamsSearchTeam
import fr.a42consulting.footballexplorer.presenter.ITeamsSearchPresenter
import fr.a42consulting.footballexplorer.presenter.TeamsSearchPresenter
import fr.a42consulting.footballexplorer.R
import fr.a42consulting.footballexplorer.view.adapter.TeamSearchGridAdapter

class TeamsSearchActivity : AppCompatActivity(), ITeamsSearchView {

    private lateinit var searchField: SearchView
    private lateinit var resultsGrid: GridView

    private lateinit var teamsSearchPresenter: ITeamsSearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_teams)

        searchField = findViewById(R.id.searchField)
        resultsGrid = findViewById(R.id.resultsGrid)

        if (lastCustomNonConfigurationInstance == null) {
            teamsSearchPresenter = TeamsSearchPresenter(this)
        } else {
            teamsSearchPresenter = lastCustomNonConfigurationInstance as ITeamsSearchPresenter
            teamsSearchPresenter.resetView(this)
        }

        searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                teamsSearchPresenter.onSearch(query)
                searchField.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        resultsGrid.setOnItemClickListener{ _, _, position, _ ->
            teamsSearchPresenter.onSelectTeam(position)
        }
    }

    override fun onSearchResult(teams: ArrayList<TeamsSearchTeam>, isNewSearch: Boolean) {
        val gridAdapter = TeamSearchGridAdapter(this, R.layout.grid_team_element)
        resultsGrid.adapter = gridAdapter
        gridAdapter.clear()
        gridAdapter.addAll(teams)
        gridAdapter.notifyDataSetChanged()
        if (isNewSearch) {
            if (teams.size == 0) {
                onMessage("La recherche n’a donné aucun résultat.")
            } else {
                onMessage(teams.size.toString() + " résultat(s) trouvé(s)")
            }
        }
    }

    override fun onShowTeam(team: String) {
        val intent = Intent(this@TeamsSearchActivity, PlayersListActivity::class.java)
        val b = Bundle()
        b.putString(PlayersListActivity.TEAM_NAME, team)
        intent.putExtras(b)
        startActivity(intent)
    }

    override fun onMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onRetainCustomNonConfigurationInstance(): ITeamsSearchPresenter {
        return teamsSearchPresenter
    }

    override fun onDestroy() {
        super.onDestroy()
        teamsSearchPresenter.resetView(null)
        searchField.setOnQueryTextListener(null)
        resultsGrid.onItemClickListener = null
        resultsGrid.adapter = null
    }
}
