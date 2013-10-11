package no.nith.pg3200.ovinger.oving2.task2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import no.nith.pg3200.ovinger.R;

/**
 * Created by Simen on 05.09.13.
 */
public class Task2 extends Activity implements View.OnClickListener {
    private ImageView imgRooney;
    private float currentRotation;
    private Button btnVisible,
            btnInvisible,
            btnRotateLeft,
            btnRotateRight,
            btnIncMargin,
            btnDecMargin;

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oving2task2);

        imgRooney = (ImageView) findViewById(R.id.imgRooney);
        btnVisible = (Button) findViewById(R.id.btnVisible);
        btnInvisible = (Button) findViewById(R.id.btnInvisible);
        btnRotateLeft = (Button) findViewById(R.id.btnRotateLeft);
        btnRotateRight = (Button) findViewById(R.id.btnRotateRigth);
        btnIncMargin = (Button) findViewById(R.id.btnIncMargin);
        btnDecMargin = (Button) findViewById(R.id.btnDecMargin);

        this.setColor(null);

        btnVisible.setOnClickListener(this);
        btnInvisible.setOnClickListener(this);
        btnRotateLeft.setOnClickListener(this);
        btnRotateRight.setOnClickListener(this);
        btnIncMargin.setOnClickListener(this);
        btnDecMargin.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        MarginLayoutParams marginParams;
        LinearLayout.LayoutParams layoutParams;
        switch (v.getId()) {
        case R.id.btnVisible:
            imgRooney.setVisibility(View.VISIBLE);
            setColor(btnVisible);
            break;
        case R.id.btnInvisible:
            imgRooney.setVisibility(View.INVISIBLE);
            setColor(btnInvisible);
            break;
        case R.id.btnIncMargin:
            marginParams = new MarginLayoutParams(imgRooney.getLayoutParams());

            marginParams.setMargins(100, 0, 100, 0);
            layoutParams = new LinearLayout.LayoutParams(marginParams);
            imgRooney.setLayoutParams(layoutParams);
            setColor(btnIncMargin);
            break;
        case R.id.btnDecMargin:
            marginParams = new MarginLayoutParams(imgRooney.getLayoutParams());

            marginParams.setMargins(0, 0, 0, 0);
            layoutParams = new LinearLayout.LayoutParams(marginParams);
            imgRooney.setLayoutParams(layoutParams);
            setColor(btnDecMargin);
            break;
        case R.id.btnRotateLeft:
            this.currentRotation = this.currentRotation == -270f ? 0f : this.currentRotation - 90f;

            imgRooney.setRotation(this.currentRotation);
            setColor(btnRotateLeft);
            break;
        case R.id.btnRotateRigth:
            this.currentRotation = this.currentRotation == 270f ? 0f : this.currentRotation + 90f;

            imgRooney.setRotation(this.currentRotation);
            setColor(btnRotateRight);
            break;
        }
    }

    private void setColor(final Button button) {
        btnVisible.setTextColor(Color.WHITE);
        btnVisible.setBackgroundColor(Color.GRAY);
        btnInvisible.setTextColor(Color.WHITE);
        btnInvisible.setBackgroundColor(Color.GRAY);
        btnIncMargin.setTextColor(Color.WHITE);
        btnIncMargin.setBackgroundColor(Color.GRAY);
        btnDecMargin.setTextColor(Color.WHITE);
        btnDecMargin.setBackgroundColor(Color.GRAY);
        btnRotateLeft.setTextColor(Color.WHITE);
        btnRotateLeft.setBackgroundColor(Color.GRAY);
        btnRotateRight.setTextColor(Color.WHITE);
        btnRotateRight.setBackgroundColor(Color.GRAY);

        if (button != null) {
            button.setTextColor(Color.BLACK);
            button.setBackgroundColor(Color.WHITE);
        }
    }
}
