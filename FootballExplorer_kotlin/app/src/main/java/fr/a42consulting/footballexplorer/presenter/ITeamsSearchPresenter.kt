package fr.a42consulting.footballexplorer.presenter

import fr.a42consulting.footballexplorer.view.ITeamsSearchView

interface ITeamsSearchPresenter {
    fun onSearch(request: String)
    fun onSelectTeam(position: Int)
    fun resetView(view:ITeamsSearchView?)
}
