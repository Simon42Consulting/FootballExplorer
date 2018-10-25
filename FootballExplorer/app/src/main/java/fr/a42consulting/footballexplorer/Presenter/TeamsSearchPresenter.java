package fr.a42consulting.footballexplorer.Presenter;

import org.json.JSONException;
import org.json.JSONObject;

import fr.a42consulting.footballexplorer.HttpGetRequest;
import fr.a42consulting.footballexplorer.Model.TeamsSearchModel;
import fr.a42consulting.footballexplorer.View.ITeamsSearchView;

public class TeamsSearchPresenter implements ITeamsSearchPresenter {
    private ITeamsSearchView searchView;
    private TeamsSearchModel league;

    public TeamsSearchPresenter(ITeamsSearchView searchView) {
        this.searchView = searchView;
    }

    @Override
    public void onSearch(String request) {
        // effectuer la recherche ici

        HttpGetRequest getRequest = new HttpGetRequest() {
            @Override
            public void onResponseReceived(String result) {
                if (result == null) {
                    league = new TeamsSearchModel();
                    searchView.onSearchResult(league.getTeams());
                    return;
                }
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    league = TeamsSearchModel.fromJson(jsonResult);
                    searchView.onSearchResult(league.getTeams());
                } catch (JSONException e) {
                    searchView.onMessage("La récupération des données a échoué.");
                    e.printStackTrace();
                }
            }
        };

        getRequest.execute("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php", "l", request);

    }

    @Override
    public void onSelectTeam(int position) {
        String teamName = league.getTeams().get(position).getName();
        searchView.onShowTeam(teamName);
    }
}
