package fr.a42consulting.footballexplorer.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import fr.a42consulting.footballexplorer.Model.TeamsSearchTeam;
import fr.a42consulting.footballexplorer.R;

public class TeamSearchGridAdapter extends ArrayAdapter<TeamsSearchTeam> {
    private Context context;

    public TeamSearchGridAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.grid_team_element, null);
        ImageView gridImage = v.findViewById(R.id.gridImage);
        Glide.with(context).load((getItem(position)).getImageUrl()).into(gridImage);
        return v;

    }
}
