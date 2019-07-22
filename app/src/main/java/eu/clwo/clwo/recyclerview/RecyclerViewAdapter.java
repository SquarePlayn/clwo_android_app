package eu.clwo.clwo.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import eu.clwo.clwo.MainActivity;
import eu.clwo.clwo.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> server_name_labels;
    private ArrayList<String> player_count_labels;
    private ArrayList<String> status_icons;
    private ArrayList<String> game_icons;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> server_name_labels, ArrayList<String> textviews_2,
                               ArrayList<String> status_icons, ArrayList<String> game_icons,
                               Context mContext) {
        this.server_name_labels = server_name_labels;
        this.player_count_labels = textviews_2;
        this.mContext = mContext;
        this.status_icons = status_icons;
        this.game_icons = game_icons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.server_status,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.server_name_label.setText(server_name_labels.get(i));

        viewHolder.player_count_label.setText(player_count_labels.get(i));

        viewHolder.status_icon.setImageResource(Integer.parseInt(status_icons.get(i)));

        viewHolder.game_icon.setImageResource(Integer.parseInt(game_icons.get(i)));

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String server_name_alert = server_name_labels.get(i);
                String player_count_alert = player_count_labels.get(i);
                String map_name_alert = MainActivity.map_names.get(i);
                String game_dir_alert = MainActivity.game_dirs.get(i);
                String ping_alert = MainActivity.pings.get(i);
                int alert_icon = Integer.parseInt(MainActivity.alert_icons.get(i));


                Toast.makeText(mContext, server_name_labels.get(i), Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(v.getContext())
                        .setIcon(alert_icon)
                        .setTitle(server_name_alert)
                        .setMessage(game_dir_alert + "\n" + player_count_alert + "\n" +
                                "Map: " + map_name_alert + "\n" + "Ping: " + ping_alert)
                        .setNegativeButton("Close", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return server_name_labels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView server_name_label;
        TextView player_count_label;
        ImageView status_icon;
        ImageView game_icon;
        RelativeLayout layout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            server_name_label = itemView.findViewById(R.id.server_name_label);
            player_count_label = itemView.findViewById(R.id.player_count_label);
            status_icon = itemView.findViewById(R.id.status_icon);
            game_icon = itemView.findViewById(R.id.game_icon);
            layout = itemView.findViewById(R.id.layout);
        }
    }

}
