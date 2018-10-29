package fr.a42consulting.footballexplorer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView

import com.bumptech.glide.Glide

import fr.a42consulting.footballexplorer.model.TeamsSearchTeam
import fr.a42consulting.footballexplorer.R

class TeamSearchGridAdapter(private val mContext: Context, private val resource: Int) : ArrayAdapter<TeamsSearchTeam>(mContext, resource) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v: View = convertView ?: LayoutInflater.from(mContext).inflate(resource, parent, false)

        val gridImage = v.findViewById<ImageView>(R.id.gridImage)
        Glide.with(mContext).load(getItem(position)!!.imageUrl).into(gridImage)
        return v
    }
}
