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
import no.nith.tic_tac_toe.FinishedTicTacToeGame;

public class SinglePlayerAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final ArrayList<FinishedTicTacToeGame> data;
    private final String ownName, winString, lossString, drawString;

    public SinglePlayerAdapter(final String ownName, final ArrayList<FinishedTicTacToeGame> data, final Activity activity) {
        this.data = data;

        this.ownName = ownName;
        this.inflater = activity.getLayoutInflater();
        this.winString = activity.getString(R.string.game_result_win);
        this.lossString = activity.getString(R.string.game_result_loss);
        this.drawString = activity.getString(R.string.game_result_draw);
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
            convertView = inflater.inflate(R.layout.single_player_list_row, null);

            holder = new ViewHolder();

            holder.txtNameView = (TextView) convertView.findViewById(R.id.nameOfOpponent);
            holder.txtDateView = (TextView) convertView.findViewById(R.id.dateOfMatch);
            holder.txtResultView = (TextView) convertView.findViewById(R.id.result);
            holder.imgArrowView = (ImageView) convertView.findViewById(R.id.imgRightArrow);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final FinishedTicTacToeGame singleGame = this.data.get(position);

        // TODO: Figure out why a ternary fails at runtime, even if evaluating the expression during debug is successful
        String nameOfOpponent = singleGame.getPlayer1Name();
        if (nameOfOpponent.equals(this.ownName)) nameOfOpponent = singleGame.getPlayer2Name();

        final String victorText = singleGame.getNameOfWinner().equals("Draw") ? this.drawString :
                singleGame.getNameOfWinner().equals(this.ownName) ? this.winString : this.lossString;

        holder.txtNameView.setText(nameOfOpponent);
        holder.txtDateView.setText(singleGame.getGameOverDate().toString());
        holder.txtResultView.setText(victorText);

        return convertView;
    }

    private static class ViewHolder {
        TextView txtNameView;
        TextView txtDateView;
        TextView txtResultView;
        ImageView imgArrowView;
    }
}
