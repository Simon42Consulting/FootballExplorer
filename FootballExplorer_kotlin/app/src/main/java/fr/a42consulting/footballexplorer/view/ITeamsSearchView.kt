package fr.a42consulting.footballexplorer.view

import java.util.ArrayList

import fr.a42consulting.footballexplorer.model.TeamsSearchTeam

interface ITeamsSearchView {
    fun onSearchResult(teams: ArrayList<TeamsSearchTeam>, isNewSearch: Boolean)
    fun onShowTeam(team: String)
    fun onMessage(message: String)
}
