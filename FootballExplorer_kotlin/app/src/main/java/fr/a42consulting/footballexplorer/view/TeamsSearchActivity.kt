package fr.a42consulting.footballexplorer.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

import java.util.ArrayList

import fr.a42consulting.footballexplorer.model.TeamsSearchTeam
import fr.a42consulting.footballexplorer.presenter.ITeamsSearchPresenter
import fr.a42consulting.footballexplorer.presenter.TeamsSearchPresenter
import fr.a42consulting.footballexplorer.R
import fr.a42consulting.footballexplorer.view.adapter.TeamSearchGridAdapter
import kotlinx.android.synthetic.main.activity_search_teams.*

class TeamsSearchActivity : AppCompatActivity(), ITeamsSearchView {
    private lateinit var teamsSearchPresenter: ITeamsSearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_teams)

        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.justifyContent = JustifyContent.CENTER

        resultsGrid.layoutManager = layoutManager

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
    }

    override fun onSearchResult(teams: ArrayList<TeamsSearchTeam>, isNewSearch: Boolean) {
        val gridAdapter = TeamSearchGridAdapter(this, teams) {
            teamsSearchPresenter.onSelectTeam(it)
        }
        resultsGrid.adapter = gridAdapter
        gridAdapter.notifyDataSetChanged()
        if (isNewSearch) {
            if (teams.size == 0) {
                onMessage(getString(R.string.teams_found_no))
            } else {
                onMessage(teams.size.toString() + getString(R.string.teams_found))
            }
        }
    }

    override fun onSearchFailed() {
        onMessage(getString(R.string.teams_found_failed))
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
        resultsGrid.adapter = null
    }
}
