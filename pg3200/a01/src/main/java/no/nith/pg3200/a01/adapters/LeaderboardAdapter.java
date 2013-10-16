package no.nith.pg3200.a01.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import no.nith.pg3200.innlevering1.R;
import no.nith.tic_tac_toe.players.AbstractPlayer;

public class LeaderboardAdapter extends BaseAdapter {
    private final String formatString;
    private LayoutInflater inflater;
    private ArrayList<AbstractPlayer> data;

    public LeaderboardAdapter(final ArrayList<AbstractPlayer> data, final Activity activity) {
        this.data = data;

        this.inflater = activity.getLayoutInflater();
        this.formatString = activity.getString(R.string.victories_leaderboard);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Way of doing it stolen from here: http://youtu.be/N6YdwzAvwOA
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.leaderboard_list_row, null);

            holder = new ViewHolder();

            holder.txtNameView = (TextView) convertView.findViewById(R.id.leaderboardName);
            holder.txtNumberView = (TextView) convertView.findViewById(R.id.leaderboardVictories);
            holder.imgArrowView = (ImageView) convertView.findViewById(R.id.imgRightArrow);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AbstractPlayer player = this.data.get(position);
        final String numberOfVictoriesText = String.format(formatString, player.getNumberOfVictories(), player.getNumberOfGames(), player.getPercentOfVictories());

        holder.txtNameView.setText(player.getName());
        holder.txtNumberView.setText(numberOfVictoriesText);

        return convertView;
    }

    private static class ViewHolder {
        TextView txtNameView;
        TextView txtNumberView;
        ImageView imgArrowView;
    }
}
