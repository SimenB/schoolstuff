package no.nith.pg3200.a02.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import no.nith.pg3200.a02.R;
import no.nith.pg3200.a02.domain.WeatherData;

public class AllForecastsAdapter extends BaseAdapter {
    private final ArrayList<WeatherData> data;
    private final LayoutInflater inflater;
    private final Resources resources;

    public AllForecastsAdapter(final ArrayList<WeatherData> data, final Activity activity) {
        this.data = data;
        this.resources = activity.getResources();
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
            convertView = inflater.inflate(R.layout.forecasts_list_row, parent, false);

            holder = new ViewHolder();

            holder.txtLonView = (TextView) convertView.findViewById(R.id.lonText);
            holder.txtLatView = (TextView) convertView.findViewById(R.id.latText);
            holder.txtDateView = (TextView) convertView.findViewById(R.id.date_txt);
            holder.imgArrowView = (ImageView) convertView.findViewById(R.id.imgRightArrow);

            final WeatherData weatherData = this.data.get(position);
            holder.txtLatView.setText(String.format(resources.getString(R.string.latitude_text), weatherData.getPosition().latitude));
            holder.txtLonView.setText(String.format(resources.getString(R.string.longitude_text), weatherData.getPosition().longitude));
            holder.txtDateView.setText(weatherData.getCreated().toString("dd/M-Y H:mm"));

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView txtLatView;
        TextView txtLonView;
        TextView txtDateView;
        ImageView imgArrowView;
    }
}
