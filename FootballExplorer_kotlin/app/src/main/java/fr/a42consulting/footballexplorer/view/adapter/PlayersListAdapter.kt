package fr.a42consulting.footballexplorer.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide

import fr.a42consulting.footballexplorer.model.PlayersListPlayer
import fr.a42consulting.footballexplorer.R
import kotlinx.android.synthetic.main.list_player_element.view.*

class PlayersListAdapter(private val mContext: Context, val items: ArrayList<PlayersListPlayer>) : RecyclerView.Adapter<PlayersListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = items[position]
        Glide.with(mContext).load(player.imageUrl).into(holder.playerImage)
        holder.playerName.text = player.name
        holder.playerRole.text = player.role
        holder.playerBirth.text = player.birth
        holder.playerPrice.text = player.price
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PlayersListAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_player_element, parent, false))
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val playerImage = itemView.playerImage!!
        val playerName = itemView.playerName!!
        val playerRole = itemView.playerRole!!
        val playerBirth = itemView.playerBirth!!
        val playerPrice = itemView.playerPrice!!
    }
}
