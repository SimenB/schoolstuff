package no.nith.pg3200.a01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import no.nith.pg3200.innlevering1.R;
import no.nith.tic_tac_toe.TicTacToeGame;
import no.nith.tic_tac_toe.exceptions.GameNotFinishedException;

public class InGame extends Activity {
    private Drawable drawableO, drawableX;
    private ImageButton[] imageButtons;
    private TicTacToeGame game;
    private TextView txtViewPlayer1, txtViewPlayer2, txtWhosTurn;
    private ImageView imgView;
    private String whosTurnFormatter;
    private Context context;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard);
        context = this;

        initViews();

        if (getIntent().getStringExtra(getString(R.string.saved_game_indic)) != null) {
            loadGame(getIntent().getStringExtra(getString(R.string.saved_game_indic)));
        } else {
            if (savedInstanceState == null) createNewGame();
            else loadGame(savedInstanceState.getString(getString(R.string.saved_game_indic)));
        }
    }

    private void initViews() {
        txtViewPlayer1 = (TextView) findViewById(R.id.txtPlayer1);
        txtViewPlayer2 = (TextView) findViewById(R.id.txtPlayer2);
        txtWhosTurn = (TextView) findViewById(R.id.txtWhosTurn);
        imgView = (ImageView) findViewById(R.id.imgResult);

        txtViewPlayer1.setTextColor(getResources().getColor(R.color.blue));
        txtViewPlayer2.setTextColor(getResources().getColor(R.color.red));

        drawableX = getResources().getDrawable(R.drawable.x);
        drawableO = getResources().getDrawable(R.drawable.o);

        whosTurnFormatter = getString(R.string.whos_turn);

        imageButtons = new ImageButton[]{
                (ImageButton) findViewById(R.id.cell0),
                (ImageButton) findViewById(R.id.cell1),
                (ImageButton) findViewById(R.id.cell2),
                (ImageButton) findViewById(R.id.cell3),
                (ImageButton) findViewById(R.id.cell4),
                (ImageButton) findViewById(R.id.cell5),
                (ImageButton) findViewById(R.id.cell6),
                (ImageButton) findViewById(R.id.cell7),
                (ImageButton) findViewById(R.id.cell8)
        };

        for (final ImageButton imageButton : this.imageButtons) {
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    clickGameboardHandler(v);
                }
            });
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        outState.putString(getString(R.string.saved_game_indic), game.serialize());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();

        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        editor.putString(getString(R.string.saved_game_indic), this.game.serialize());

        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        final String serializedGame = preferences.getString(getString(R.string.saved_game_indic), "");

        preferences.edit().remove(getString(R.string.saved_game_indic)).apply();

        if (serializedGame.length() > 0) this.loadGame(serializedGame);
    }

    private void createNewGame() {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        final String nameOfPlayer1 = preferences.getString(getString(R.string.pref_key_name1), getString(R.string.default_name1)),
                nameOfPlayer2 = preferences.getString(getString(R.string.pref_key_name2), getString(R.string.default_name2));

        game = new TicTacToeGame(2, nameOfPlayer1, nameOfPlayer2);

        txtViewPlayer1.setText(game.getPlayer1().getName());
        txtViewPlayer2.setText(game.getPlayer2().getName());

        txtWhosTurn.setText(String.format(whosTurnFormatter, game.getPlayer1().getName()));
        txtWhosTurn.setTextColor(getResources().getColor(R.color.blue));
    }

    private void loadGame(final String data) {
        game = TicTacToeGame.deserialize(data);

        for (int i = 0; i < game.getCurrentBoard().length; i++) {
            int currentCell = game.getCurrentBoard()[i];

            if (currentCell != 0) {
                final ImageButton imageButton = imageButtons[i];
                imageButton.setEnabled(false);
                imageButton.setImageDrawable(currentCell == 1 ? drawableX : drawableO);
            }
        }

        txtViewPlayer1.setText(game.getPlayer1().getName());
        txtViewPlayer2.setText(game.getPlayer2().getName());

        final String nameOfCurrentPlayer = this.game.isPlayer1Turn() ? game.getPlayer1().getName() : game.getPlayer2().getName();
        final int colorOfCurrentPlayer = this.game.isPlayer1Turn() ? getResources().getColor(R.color.blue) : getResources().getColor(R.color.red);

        txtWhosTurn.setText(String.format(whosTurnFormatter, nameOfCurrentPlayer));
        txtWhosTurn.setTextColor(colorOfCurrentPlayer);
    }

    private void clickGameboardHandler(final View v) {
        if (!v.isEnabled()) return;

        final ImageButton button = (ImageButton) v;
        boolean success;
        int victor;

        final Drawable currentDrawable = this.game.isPlayer1Turn() ? drawableX : drawableO;

        for (int i = 0; i < this.imageButtons.length; i++) {
            if (this.imageButtons[i].getId() == button.getId()) {
                success = this.game.placeChar(i);
                if (success) {
                    button.setImageDrawable(currentDrawable);
                    this.imageButtons[i].setEnabled(false);

                    final String nameOfCurrentPlayer = this.game.isPlayer1Turn() ? game.getPlayer1().getName() : game.getPlayer2().getName();
                    final int colorOfCurrentPlayer = this.game.isPlayer1Turn() ? getResources().getColor(R.color.blue) : getResources().getColor(R.color.red);

                    txtWhosTurn.setText(String.format(whosTurnFormatter, nameOfCurrentPlayer));
                    txtWhosTurn.setTextColor(colorOfCurrentPlayer);
                }
            }
        }

        victor = game.checkForVictoryOrDraw();

        if (victor != 0) {
            this.gameOver(victor);
        }
    }

    private void gameOver(int victor) {
        for (ImageButton currButton : imageButtons) {
            currButton.setEnabled(false);
        }

        String victorText = "";

        switch (victor) {
        case 1:
            victorText = String.format(getString(R.string.congratulations), game.getPlayer1().getName());
            txtViewPlayer1.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.textsize_of_victor));
            imgView.setImageResource(R.drawable.hi_res_x);
            txtWhosTurn.setTextColor(getResources().getColor(R.color.blue));

            break;
        case 2:
            victorText = String.format(getString(R.string.congratulations), game.getPlayer2().getName());
            txtViewPlayer2.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.textsize_of_victor));
            imgView.setImageResource(R.drawable.hi_res_o);
            txtWhosTurn.setTextColor(getResources().getColor(R.color.red));

            break;
        case 3:
            victorText = getString(R.string.draw_result);
            imgView.setImageResource(R.drawable.hi_res_draw);
            txtWhosTurn.setTextColor(getResources().getColor(R.color.android_default_grey));

            break;
        }

        imgView.setVisibility(ImageView.VISIBLE);
        txtWhosTurn.setText(victorText);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    final Intent intent = new Intent(context, PostGame.class);
                    intent.putExtra("prevGame", game.getFinishedGame().serialize());
                    startActivity(intent);
                    finish();
                } catch (GameNotFinishedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }
}
