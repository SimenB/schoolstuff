package no.nith.pg3200.a02.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import no.nith.pg3200.a02.R;
import no.nith.pg3200.a02.domain.Forecast;
import no.nith.pg3200.a02.utils.Utils;

/**
 * @author Simen Bekkhus
 */
public class SingleForecastAdapter extends BaseAdapter {
    private final List<Forecast> data;
    private final LayoutInflater inflater;
    private final Resources resources;

    public SingleForecastAdapter(final List<Forecast> data, final Activity activity) {
        this.data = data;
        this.resources = activity.getResources();
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Forecast getItem(final int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    // Way of doing it stolen from here: http://youtu.be/N6YdwzAvwOA
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final View view = inflater.inflate(R.layout.forecast_list_row, parent, false);

        final ImageView imgIconView = (ImageView) view.findViewById(R.id.imgWeatherIcon);
        final TextView txtTimeView = (TextView) view.findViewById(R.id.txtHour);
        final TextView txtDegreesView = (TextView) view.findViewById(R.id.txtTemp);

        final Forecast forecast = this.getItem(position);
        final double temperature = forecast.getTemperature();
        final int degreeColor = temperature < 0 ? R.color.blue : temperature > 0 ? R.color.red : R.color.almost_black;

        imgIconView.setImageResource(Utils.getIcon(forecast.getSymbol()));
        txtTimeView.setText(forecast.getTime().toString("dd/M H:mm"));
        txtDegreesView.setText(String.format(resources.getString(R.string.degrees), temperature));
        txtDegreesView.setTextColor(resources.getColor(degreeColor));

        return view;
    }
}
