package fr.a42consulting.footballexplorer.Presenter;

import org.json.JSONException;
import org.json.JSONObject;

import fr.a42consulting.footballexplorer.HttpGetRequest;
import fr.a42consulting.footballexplorer.Model.PlayersListModel;
import fr.a42consulting.footballexplorer.View.IPlayersListView;

public class PlayersListPresenter implements IPlayersListPresenter {
    private IPlayersListView playersListView;
    private PlayersListModel team;

    public PlayersListPresenter(IPlayersListView listView) {
        this.playersListView = listView;
    }

    @Override
    public void onLoadPlayers(String request) {
        HttpGetRequest getRequest = new HttpGetRequest() {
            @Override
            public void onResponseReceived(String result) {
                if (result == null) {
                    team = new PlayersListModel();
                    playersListView.onRequestResult(team.getPlayers());
                    return;
                }
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    team = PlayersListModel.fromJson(jsonResult);
                    playersListView.onRequestResult(team.getPlayers());
                } catch (JSONException e) {
                    playersListView.onMessage("La récupération des données a échoué.");
                    e.printStackTrace();
                }
            }
        };

        getRequest.execute("https://www.thesportsdb.com/api/v1/json/1/searchplayers.php", "t", request);
    }
}
