package no.nith.pg3200.exercises.oving2.task4;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Simen on 06.09.13.
 */
public class FragYo extends Fragment {
    private String intro = "halla";

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setText(Integer.toString(getArguments().getInt(intro)));
        return textView;
    }
}
