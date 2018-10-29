package fr.a42consulting.footballexplorer.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.a42consulting.footballexplorer.Model.TeamsSearchTeam;
import fr.a42consulting.footballexplorer.Presenter.ITeamsSearchPresenter;
import fr.a42consulting.footballexplorer.Presenter.TeamsSearchPresenter;
import fr.a42consulting.footballexplorer.R;

public class TeamsSearchActivity extends AppCompatActivity implements ITeamsSearchView {

    SearchView searchField;
    GridView resultsGrid;

    ITeamsSearchPresenter teamsSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_teams);

        //Init view
        searchField = findViewById(R.id.searchField);
        resultsGrid = findViewById(R.id.resultsGrid);

        //Init
        teamsSearchPresenter = new TeamsSearchPresenter(this);

        searchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                teamsSearchPresenter.onSearch(query);
                searchField.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        resultsGrid.setOnItemClickListener(new GridView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                teamsSearchPresenter.onSelectTeam(position);
            }
        });
    }

    @Override
    public void onSearchResult(ArrayList<TeamsSearchTeam> teams) {
        TeamSearchGridAdapter gridAdapter = new TeamSearchGridAdapter(this, R.layout.grid_team_element);
        resultsGrid.setAdapter(gridAdapter);
        gridAdapter.addAll(teams);
        gridAdapter.notifyDataSetChanged();
        if (teams.size() == 0) {
            onMessage("La recherche n’a donné aucun résultat.");
        } else {
            onMessage(teams.size() + " résultat(s) trouvé(s)");
        }
    }

    @Override
    public void onShowTeam(String team) {
        Intent intent = new Intent(TeamsSearchActivity.this, PlayersListActivity.class);
        Bundle b = new Bundle();
        b.putString(PlayersListActivity.TEAM_NAME, team);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public void onMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
