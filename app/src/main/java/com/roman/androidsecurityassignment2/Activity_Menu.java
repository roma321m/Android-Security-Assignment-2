package com.roman.androidsecurityassignment2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Menu extends AppCompatActivity {
    private MaterialButton    menu_BTN_start;
    private TextInputEditText menu_EDT_id;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        initViews();
    }

    private void initViews() {
        this.menu_BTN_start.setOnClickListener(v -> {
            if (Objects.requireNonNull(this.menu_EDT_id.getText()).toString().length() != 9) {
                return;
            }
            Activity_Menu.this.makeServerCall();
        });
    }

    private void findViews() {
        this.menu_BTN_start = findViewById(R.id.menu_BTN_start);
        this.menu_EDT_id = findViewById(R.id.menu_EDT_id);
    }

    /* access modifiers changed from: private */
    private void makeServerCall() {
        new Thread() {
            public void run() {
                String data = Activity_Menu.getJSON(Activity_Menu.this.getString(R.string.url));
                Log.d("pttt", data);
                if (data != null) {
                    Activity_Menu activity_Menu = Activity_Menu.this;
                    activity_Menu.startGame(Objects.requireNonNull(activity_Menu.menu_EDT_id.getText()).toString(), data);
                }
            }
        }.start();
    }

    private void startGame(String id, String data) {
        String state = data.split(",")[Integer.parseInt(String.valueOf(id.charAt(7)))];
        Intent intent = new Intent(getBaseContext(), Activity_Game.class);
        Log.d("pttt", id);
        Log.d("pttt", state);
        intent.putExtra(Activity_Game.EXTRA_ID, id);
        intent.putExtra(Activity_Game.EXTRA_STATE, state);
        startActivity(intent);
    }

    public static String getJSON(String url) {
        String data = "";
        try {
            HttpsURLConnection con2 = (HttpsURLConnection) new URL(url).openConnection();
            con2.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(con2.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = br.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine).append("\n");
            }
            br.close();
            data = sb.toString();
            try {
                con2.disconnect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex2) {
            ex2.printStackTrace();
        }
        return data;
    }
}
