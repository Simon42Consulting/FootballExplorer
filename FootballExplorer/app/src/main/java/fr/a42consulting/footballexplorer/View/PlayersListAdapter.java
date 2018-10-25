package fr.a42consulting.footballexplorer.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.a42consulting.footballexplorer.Model.PlayersListPlayer;
import fr.a42consulting.footballexplorer.R;

public class PlayersListAdapter extends ArrayAdapter<PlayersListPlayer> {
    private Context context;

    public PlayersListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_player_element, null);

        PlayersListPlayer player = getItem(position);
        ImageView playerImage = v.findViewById(R.id.playerImage);
        TextView playerName = v.findViewById(R.id.playerName);
        TextView playerRole = v.findViewById(R.id.playerRole);
        TextView playerBirth = v.findViewById(R.id.playerBirth);
        TextView playerPrice = v.findViewById(R.id.playerPrice);

        Glide.with(context).load(player.getImageUrl()).into(playerImage);
        playerName.setText(player.getName());
        playerBirth.setText(player.getBirth());
        playerRole.setText(player.getRole());
        playerPrice.setText(player.getPrice());

        return v;
    }
}
