package no.nith.pg3200.a02.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import no.nith.pg3200.a02.R;
import no.nith.pg3200.a02.domain.Forecast;
import no.nith.pg3200.a02.utils.Utils;

public class SingleForecastAdapter extends BaseAdapter {
    private final ArrayList<Forecast> data;
    private final LayoutInflater inflater;
    private final Activity activity;

    public SingleForecastAdapter(final ArrayList<Forecast> data, final Activity activity) {
        this.data = data;
        this.activity = activity;
        this.inflater = activity.getLayoutInflater();
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
            convertView = inflater.inflate(R.layout.forecast_list_row, parent, false);

            holder = new ViewHolder();

            holder.imgIconView = (ImageView) convertView.findViewById(R.id.imgWeatherIcon);
            holder.txtTimeView = (TextView) convertView.findViewById(R.id.txtHour);
            holder.txtDeggreesView = (TextView) convertView.findViewById(R.id.txtTemp);

            final Forecast forecast = this.data.get(position);
            holder.imgIconView.setImageResource(Utils.getIcon(forecast.getSymbol()));
            holder.txtTimeView.setText(forecast.getTime().toString("dd/M H:mm"));
            holder.txtDeggreesView.setText(String.format(activity.getString(R.string.degrees), forecast.getTemperature()));

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private static class ViewHolder {
        ImageView imgIconView;
        TextView txtTimeView;
        TextView txtDeggreesView;
    }
}
