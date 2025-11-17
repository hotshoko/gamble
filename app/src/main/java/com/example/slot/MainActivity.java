package com.example.slot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int a, b, c, d, e, f, num, count, played;
    private String name;
    private TextView at, bt, ct, dt, et, ft, numt, countWin;
    private EditText nameet;
    private Button start,newGame,score;

    public static int countAll, gamesPlayed;
    private boolean isRunning; // Renamed for clarity
    private Handler handler;
    private Runnable numberGeneratorRunnable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.button);
        at = findViewById(R.id.aID);
        bt = findViewById(R.id.bID);
        ct = findViewById(R.id.cID);
        dt = findViewById(R.id.dID);
        et = findViewById(R.id.eID);
        ft = findViewById(R.id.fID);
        numt = findViewById(R.id.numID);
        countWin = findViewById(R.id.countWinID);
        newGame = findViewById(R.id.newGameID);
        score = findViewById(R.id.ScoreID);
        nameet = findViewById(R.id.nameEt);
        count=0;
        played=0;
        gamesPlayed=0;
        countAll=0;
        Intent scoreAc = new Intent(MainActivity.this, ScoreActivity.class);

        // --- Initial setup for the 6 static numbers ---
        a = (int) (Math.random() * 39) + 1;
        b = (int) (Math.random() * 39) + 1;
        c = (int) (Math.random() * 39) + 1;
        d = (int) (Math.random() * 39) + 1;
        e = (int) (Math.random() * 39) + 1;
        f = (int) (Math.random() * 39) + 1;

        while (a==b||a==c||a==d||a==e||a==f){
            a = (int) (Math.random() * 39) + 1;
        }
        while (b==a||b==c||b==d||b==e||b==f){
            b = (int) (Math.random() * 39) + 1;
        }
        while (c==a||c==b||c==d||c==e||c==f){
            c = (int) (Math.random() * 39) + 1;
        }
        while (d==a||d==b||d==c||d==e||d==f){
            d = (int) (Math.random() * 39) + 1;
        }
        while (e==a||e==b||e==c||e==d||e==f){
            e = (int) (Math.random() * 39) + 1;
        }
        while (f==a||f==b||f==c||f==d||f==e){
            f = (int) (Math.random() * 39) + 1;
        }
        at.setText(String.valueOf(a));
        bt.setText(String.valueOf(b));
        ct.setText(String.valueOf(c));
        dt.setText(String.valueOf(d));
        et.setText(String.valueOf(e));
        ft.setText(String.valueOf(f));

        // --- State and Handler setup ---
        isRunning = false; // The process is not running initially
        handler = new Handler(Looper.getMainLooper());

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = !isRunning;

                if (isRunning&&played<6) {
                    start.setText("Stop");
                    start.setBackgroundColor(Color.RED);
                    numberGeneratorRunnable = new Runnable() {
                        @Override
                        public void run() {
                            num = (int) (Math.random() * 39) + 1;
                            numt.setText(String.valueOf(num));

                            handler.postDelayed(this, 1000);
                        }
                    };
                    handler.post(numberGeneratorRunnable);

                } else {
                    start.setText("Start");
                    start.setBackgroundColor(Color.GREEN);
                    if (num==a&&played<6) {
                        at.setBackgroundColor(Color.RED);
                        count++;
                        countWin.setText(count+" of 6");
                    }
                    if(num==b&&played<6) {
                        bt.setBackgroundColor(Color.RED);
                        count++;
                        countWin.setText(count+" of 6");

                    }
                    if(num==c&&played<6) {
                        ct.setBackgroundColor(Color.RED);
                        count++;
                        countWin.setText(count+" of 6");
                    }
                    if(num==d&&played<6) {
                        dt.setBackgroundColor(Color.RED);
                        count++;
                        countWin.setText(count+" of 6");
                    }
                    if(num==e&&played<6) {
                        et.setBackgroundColor(Color.RED);
                        count++;
                        countWin.setText(count+" of 6");
                    }
                    if(num==f&&played<6) {
                        ft.setBackgroundColor(Color.RED);
                        count++;
                        countWin.setText(count+" of 6");
                    }
                    if (numberGeneratorRunnable != null) {
                        handler.removeCallbacks(numberGeneratorRunnable);
                    }
                    played++;
                }
            }
        });
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (played>5) {
                    a = (int) (Math.random() * 39) + 1;
                    b = (int) (Math.random() * 39) + 1;
                    c = (int) (Math.random() * 39) + 1;
                    d = (int) (Math.random() * 39) + 1;
                    e = (int) (Math.random() * 39) + 1;
                    f = (int) (Math.random() * 39) + 1;

                    at.setText(String.valueOf(a));
                    bt.setText(String.valueOf(b));
                    ct.setText(String.valueOf(c));
                    dt.setText(String.valueOf(d));
                    et.setText(String.valueOf(e));
                    ft.setText(String.valueOf(f));

                    at.setBackgroundColor(Color.WHITE);
                    bt.setBackgroundColor(Color.WHITE);
                    ct.setBackgroundColor(Color.WHITE);
                    dt.setBackgroundColor(Color.WHITE);
                    et.setBackgroundColor(Color.WHITE);
                    ft.setBackgroundColor(Color.WHITE);
                    isRunning = false;
                    countAll += count;
                    played = 0;
                    count = 0;
                    gamesPlayed++;
                    countWin.setText(count + " of 6");
                    start.setText("Start");
                }
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameet.getText().toString();
                scoreAc.putExtra("NAME",name);
                startActivity(scoreAc);
            }
        });
    }
}