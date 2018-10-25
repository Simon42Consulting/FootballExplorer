package fr.a42consulting.footballexplorer.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.a42consulting.footballexplorer.Model.PlayersListPlayer;
import fr.a42consulting.footballexplorer.Presenter.IPlayersListPresenter;
import fr.a42consulting.footballexplorer.Presenter.PlayersListPresenter;
import fr.a42consulting.footballexplorer.R;

public class PlayersListActivity extends AppCompatActivity implements IPlayersListView {
    public static final String TEAM_NAME = "INTENT_PARAM_TEAM_NAME";

    ListView playersList;

    IPlayersListPresenter playersListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);
        Bundle bundle = getIntent().getExtras();
        String team = "";
        if (bundle != null) {
            team = bundle.getString(TEAM_NAME);
        }

        //Init view
        playersList = findViewById(R.id.playersList);

        playersListPresenter = new PlayersListPresenter(this);
        playersListPresenter.onLoadPlayers(team);
    }

    @Override
    public void onRequestResult(ArrayList<PlayersListPlayer> players) {
        PlayersListAdapter listAdapter = new PlayersListAdapter(this, R.layout.list_player_element);
        playersList.setAdapter(listAdapter);
        listAdapter.addAll(players);
        listAdapter.notifyDataSetChanged();
        if (players.size() == 0) {
            onMessage("Aucun joueur trouvé.");
        } else {
            onMessage(players.size() + " joueurs(s) trouvé(s)");
        }
    }

    @Override
    public void onMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
