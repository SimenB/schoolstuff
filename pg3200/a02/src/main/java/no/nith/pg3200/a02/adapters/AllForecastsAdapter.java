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

/**
 * @author Simen Bekkhus
 */
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
    public WeatherData getItem(final int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final View view = inflater.inflate(R.layout.forecasts_list_row, parent, false);

        final TextView txtLonView = (TextView) view.findViewById(R.id.lonText);
        final TextView txtLatView = (TextView) view.findViewById(R.id.latText);
        final TextView txtDateView = (TextView) view.findViewById(R.id.date_txt);
        final ImageView imgArrowView = (ImageView) view.findViewById(R.id.imgRightArrow);

        final WeatherData weatherData = this.getItem(position);
        txtLatView.setText(String.format(resources.getString(R.string.latitude_text), weatherData.getPosition().latitude));
        txtLonView.setText(String.format(resources.getString(R.string.longitude_text), weatherData.getPosition().longitude));
        txtDateView.setText(weatherData.getCreated().toString("dd/M-Y H:mm"));

        return view;
    }
}
