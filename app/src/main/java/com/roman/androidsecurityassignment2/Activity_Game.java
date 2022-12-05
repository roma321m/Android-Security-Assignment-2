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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        String id = getIntent().getStringExtra(EXTRA_ID);
        getSteps(id);

        findViews();
        initViews();
    }

    private void getSteps(String id) {
        if (id.length() == this.steps.length) {
            int i = 0;
            int[] iArr = this.steps;
            StringBuilder mySteps = new StringBuilder("My steps: \n");
            while (i != iArr.length) { // todo
                iArr[i] = Integer.parseInt(String.valueOf(id.charAt(i))) % 4;
                mySteps.append(i+1);
                mySteps.append(") ");
                switch (iArr[i]) {
                    case 0:
                        mySteps.append(" left \n");
                        break;
                    case 1:
                        mySteps.append(" right \n");
                        break;
                    case 2:
                        mySteps.append(" Up \n");
                        break;
                    case 3:
                        mySteps.append(" Down \n");
                        break;
                    default:
                        break;
                }
                i++;
            }
            Log.d("pttt", mySteps.toString());
        }
    }

    private void arrowClicked(int direction) {
        if (this.goodToGo && direction != this.steps[this.currentLevel]) {
            this.goodToGo = false;
        }
        this.currentLevel ++;
        if (this.currentLevel >= this.steps.length) {
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
                findViewById(R.id.game_BTN_left),
                findViewById(R.id.game_BTN_right),
                findViewById(R.id.game_BTN_up),
                findViewById(R.id.game_BTN_down)
        };
    }
}
