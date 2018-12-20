package fr.a42consulting.footballexplorer.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide

import fr.a42consulting.footballexplorer.model.TeamsSearchTeam
import fr.a42consulting.footballexplorer.R

class TeamSearchGridAdapter(private val mContext: Context, val items:ArrayList<TeamsSearchTeam>, private val listener: (Int) -> Unit ) : RecyclerView.Adapter<TeamSearchGridAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return TeamSearchGridAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.grid_team_element, parent, false) as ImageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = items[position]
        Glide.with(mContext).load(team.imageUrl).into(holder.image)
        holder.image.contentDescription = team.name
        holder.bind(position, listener)
    }

    class ViewHolder(val image: ImageView) : RecyclerView.ViewHolder(image) {
        fun bind(pos: Int, listener: (Int) -> Unit) {
            image.setOnClickListener {
                listener(pos)
            }
        }
    }
}
