package com.example.drawingpen;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;

import android.graphics.Path;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;




public class MainActivity extends AppCompatActivity {

    Paper board;
    private Button red,green,blue,black;
    private  Button CLEAR,CLEANALL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.argb(255,255,255,255));


        board=findViewById(R.id.Board);
        red=findViewById(R.id.red);
        green=findViewById(R.id.green);
        blue=findViewById(R.id.blue);
        black=findViewById(R.id.black);


        CLEANALL=findViewById(R.id.cleanall);
        CLEAR=findViewById(R.id.button);

        CLEAR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                board.clear();
            }
        });
        CLEANALL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                board.clearAll();
            }
        });

     red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.RED=255;
                board.BLUE=0;
                board.GREEN=0;

            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.RED=0;
                board.BLUE=255;
                board.GREEN=0;

            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.RED=0;
                board.BLUE=0;
                board.GREEN=255;

            }
        });
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.RED=0;
                board.BLUE=0;
                board.GREEN=0;


            }
        });




    }




}
