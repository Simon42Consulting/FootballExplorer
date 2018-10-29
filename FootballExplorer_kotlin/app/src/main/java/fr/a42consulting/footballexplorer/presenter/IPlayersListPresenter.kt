package fr.a42consulting.footballexplorer.presenter

import fr.a42consulting.footballexplorer.view.IPlayersListView

interface IPlayersListPresenter {
    fun onLoadPlayers(request: String)
    fun resetView(view:IPlayersListView?)
}
