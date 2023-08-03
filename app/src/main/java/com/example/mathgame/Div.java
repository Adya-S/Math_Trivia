package com.example.mathgame;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Div extends AppCompatActivity {

    TextView score, life, time, ques;

    EditText ans;

    Button ok, nxt;

    Random random = new Random();
    int num1, num2, user_ans, correct_ans, user_score = 0, user_life = 3;

    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 60000;
    Boolean timer_running;
    long time_left_in_milis = START_TIMER_IN_MILIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_div);

        score = findViewById(R.id.Score);
        life = findViewById(R.id.Life);
        time = findViewById(R.id.Time);
        ques = findViewById(R.id.ques);
        ans = findViewById(R.id.Ans);
        ok = findViewById(R.id.btn_ok);
        nxt = findViewById(R.id.btn_nxt);

        GameCont();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_ans = Integer.valueOf(ans.getText().toString());

                pauseTimer();

                if(user_ans == correct_ans)
                {
                    user_score += 10;
                    score.setText(""+user_score);
                    ques.setText("CONGRATULATIONS!!! Correct Answer");
                }
                else
                {
                    user_life -= 1;
                    life.setText(""+user_life);
                    ques.setText("SORRY!!! Wrong Answer");
                }
            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans.setText("");
                resetTimer();

                if(user_life <= 0)
                {
                    Toast.makeText(getApplicationContext(),"Game Over", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Div.this, Result.class);
                    intent.putExtra("score",user_score);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    GameCont();
                }
            }
        });

    }

    public void GameCont()
    {
        num1 = random.nextInt(200);
        num2 = random.nextInt(100);

        correct_ans = num1 / num2;

        ques.setText(num1 + " / " + num2);

        startTimer();

    }

    public void startTimer()
    {
        timer = new CountDownTimer(time_left_in_milis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateTimer();
                user_life -= 1;
                life.setText(""+user_life);
                ques.setText("SORRY!!! Times Up");
            }
        }.start();

        timer_running = true;
    }

    public void updateTimer()
    {
        int sec = (int)(time_left_in_milis / 1000) % 60;
        String time_left = String.format(Locale.getDefault(),"%02d", sec);
        time.setText(time_left);
    }

    public void pauseTimer()
    {
        timer.cancel();
        timer_running = false;
    }

    public void resetTimer()
    {
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateTimer();
    }
}