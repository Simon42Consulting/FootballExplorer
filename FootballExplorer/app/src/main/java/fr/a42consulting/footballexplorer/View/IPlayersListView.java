package fr.a42consulting.footballexplorer.View;

import java.util.ArrayList;

import fr.a42consulting.footballexplorer.Model.PlayersListPlayer;

public interface IPlayersListView {
    void onRequestResult(ArrayList<PlayersListPlayer> players);
    void onMessage(String message);
}
