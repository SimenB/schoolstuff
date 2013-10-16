package no.nith.pg3200.exercises.oving3.task2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import no.nith.pg3200.ovinger.R;

public class WordList extends Activity {
    private EditText input;
    private Button btnAdd;
    private ListView listView;
    private List<String> listOfWords;
    private AwesomeAdapter adapter;
    private Context context;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oving3task2);

        input = (EditText) findViewById(R.id.exercise3task2input);
        btnAdd = (Button) findViewById(R.id.exercise3task2btn_add);
        listView = (ListView) findViewById(android.R.id.list);
        listOfWords = new ArrayList<String>(Arrays.asList("halla", "test"));
        context = this;

        adapter = new AwesomeAdapter(this, android.R.layout.simple_list_item_1, listOfWords);

        listView.setAdapter(adapter);

        this.addListeners();
    }

    private boolean addListeners() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                addToList();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> myAdapter, final View myView, final int myItemInt, final long mylng) {
                final String selectedFromList = (String) listView.getItemAtPosition(myItemInt);
                final Intent intent = new Intent(context, ViewWord.class);

                intent.putExtra("word", selectedFromList);

                startActivity(intent);
            }
        });

        input.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(final View v, final int keyCode, final KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    addToList();
                    return true;
                }
                return false;
            }
        });

        return true;
    }

    private void addToList() {
        final String text = input.getText().toString();

        if (text.length() > 0) {
            listOfWords.add(text);
            adapter.notifyDataSetChanged();

            input.setText("");
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.not_text_toast), Toast.LENGTH_SHORT).show();
        }
    }

    private static class AwesomeAdapter extends ArrayAdapter {
        private final String[] colors = new String[]{
                "black", "darkgray", "gray", "lightgray", "white", "red", "green", "blue", "yellow", "cyan", "magenta",
                "aqua", "fuchsia", "darkgrey", "grey", "lightgrey", "lime", "maroon", "navy", "olive", "purple",
                "silver"
        };
        private Random random;

        public AwesomeAdapter(final Context context, final int resource, final List objects) {
            super(context, resource, objects);

            random = new Random();
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            final View view = super.getView(position, convertView, parent);

            view.setBackgroundColor(Color.parseColor(colors[random.nextInt(colors.length)]));

            return view;
        }
    }
}
