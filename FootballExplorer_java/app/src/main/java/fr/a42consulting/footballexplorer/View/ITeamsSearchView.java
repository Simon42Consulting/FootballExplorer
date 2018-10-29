package fr.a42consulting.footballexplorer.View;

import java.util.ArrayList;

import fr.a42consulting.footballexplorer.Model.TeamsSearchTeam;

public interface ITeamsSearchView {
    void onSearchResult(ArrayList<TeamsSearchTeam> teams);
    void onShowTeam(String team);
    void onMessage(String message);
}
