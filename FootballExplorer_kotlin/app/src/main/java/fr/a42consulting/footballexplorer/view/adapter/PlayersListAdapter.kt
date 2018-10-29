package fr.a42consulting.footballexplorer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import fr.a42consulting.footballexplorer.model.PlayersListPlayer
import fr.a42consulting.footballexplorer.R

class PlayersListAdapter(private val mContext: Context, private val resource: Int) : ArrayAdapter<PlayersListPlayer>(mContext, resource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v: View = convertView ?: LayoutInflater.from(mContext).inflate(resource, parent, false)

        val player = getItem(position)
        val playerImage = v.findViewById<ImageView>(R.id.playerImage)
        val playerName = v.findViewById<TextView>(R.id.playerName)
        val playerRole = v.findViewById<TextView>(R.id.playerRole)
        val playerBirth = v.findViewById<TextView>(R.id.playerBirth)
        val playerPrice = v.findViewById<TextView>(R.id.playerPrice)

        Glide.with(mContext).load(player!!.imageUrl).into(playerImage)
        playerName.text = player.name
        playerBirth.text = player.birth
        playerRole.text = player.role
        playerPrice.text = player.price

        return v
    }
}
