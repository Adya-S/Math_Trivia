package com.example.mathgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button add, sub, mul, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.btn_add);
        sub = findViewById(R.id.btn_sub);
        mul = findViewById(R.id.btn_mul);
        div = findViewById(R.id.btn_div);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,M_game.class);
                startActivity(intent);
                finish();

            }
        });

        sub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Sub.class);
                startActivity(intent);
                finish();

            }
        });

        mul.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Mul.class);
                startActivity(intent);
                finish();

            }
        });

        div.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Div.class);
                startActivity(intent);
                finish();

            }
        });
    }
}