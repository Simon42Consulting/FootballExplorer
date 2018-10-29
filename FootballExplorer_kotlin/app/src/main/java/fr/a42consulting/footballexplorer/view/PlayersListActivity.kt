package fr.a42consulting.footballexplorer.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast

import java.util.ArrayList

import fr.a42consulting.footballexplorer.model.PlayersListPlayer
import fr.a42consulting.footballexplorer.presenter.IPlayersListPresenter
import fr.a42consulting.footballexplorer.presenter.PlayersListPresenter
import fr.a42consulting.footballexplorer.R
import fr.a42consulting.footballexplorer.view.adapter.PlayersListAdapter

class PlayersListActivity : AppCompatActivity(), IPlayersListView {

    private lateinit var playersList: ListView

    private lateinit var playersListPresenter: IPlayersListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_list)

        val team = intent.getStringExtra(TEAM_NAME)

        playersList = findViewById(R.id.playersList)

        playersListPresenter = PlayersListPresenter(this)
        playersListPresenter.onLoadPlayers(team)
    }

    override fun onRequestResult(players: ArrayList<PlayersListPlayer>) {
        val listAdapter = PlayersListAdapter(this, R.layout.list_player_element)
        playersList.adapter = listAdapter
        listAdapter.addAll(players)
        listAdapter.notifyDataSetChanged()
        if (players.size == 0) {
            onMessage("Aucun joueur trouvé.")
        } else {
            onMessage(players.size.toString() + " joueurs(s) trouvé(s)")
        }
    }

    override fun onMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        playersListPresenter.resetView(null)
        playersList.adapter = null
    }

    companion object {
        const val TEAM_NAME = "INTENT_PARAM_TEAM_NAME"
    }
}
