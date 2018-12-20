package fr.a42consulting.footballexplorer.view

import java.util.ArrayList

import fr.a42consulting.footballexplorer.model.PlayersListPlayer

interface IPlayersListView {
    fun onRequestResult(players: ArrayList<PlayersListPlayer>)
    fun onMessage(message: String)
    fun onRequestFailed()
}
