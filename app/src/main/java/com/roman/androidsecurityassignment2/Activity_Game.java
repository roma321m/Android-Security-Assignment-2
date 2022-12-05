package com.roman.androidsecurityassignment2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Game extends AppCompatActivity {
    public static final String        EXTRA_ID    = "EXTRA_ID";
    public static final String        EXTRA_STATE = "EXTRA_STATE";
    private             ImageButton[] arrows;
    int currentLevel = 0;
    private boolean goodToGo = true;
    int[] steps = {1, 1, 1, 2, 2, 2, 3, 3, 3};

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_game);
        String id = getIntent().getStringExtra(EXTRA_ID);
        if (id.length() == this.steps.length) {
            int i = 0;
            int[] iArr = this.steps;
            while (i != iArr.length) { // todo
                iArr[i] = Integer.parseInt(String.valueOf(id.charAt(i))) % 4;
                i++;
            }
            Log.d("tppp", "iArr: [" + iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4] + iArr[5] + iArr[6] + iArr[7] + iArr[8] + "]");
        }
        findViews();
        initViews();
    }

    /* access modifiers changed from: private */
    public void arrowClicked(int direction) {
        if (this.goodToGo && direction != this.steps[this.currentLevel]) {
            this.goodToGo = false;
        }
        int i = this.currentLevel + 1;
        this.currentLevel = i;
        if (i >= this.steps.length) {
            finishGame();
        }
    }

    private void finishGame() {
        String state = getIntent().getStringExtra(EXTRA_STATE);
        if (this.goodToGo) {
            Toast.makeText(this, "Survived in " + state, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "You Failed ", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    private void initViews() {
        int i = 0;
        while (true) {
//            ImageButton[] imageButtonArr = this.arrows;
            if (i < arrows.length) {
                final int finalI = i;
                arrows[i].setOnClickListener(v -> Activity_Game.this.arrowClicked(finalI));
                i++;
            }
            else {
                return;
            }
        }
    }

    private void findViews() {
        this.arrows = new ImageButton[]{
                (ImageButton) findViewById(R.id.game_BTN_left),
                (ImageButton) findViewById(R.id.game_BTN_right),
                (ImageButton) findViewById(R.id.game_BTN_up),
                (ImageButton) findViewById(R.id.game_BTN_down)
        };
    }
}
