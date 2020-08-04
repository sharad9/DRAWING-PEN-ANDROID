package com.example.drawingpen;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;

import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;



public class MainActivity extends AppCompatActivity {

Paper board;
    private SeekBar red,green,blue;
    private  Button CLEAR,SETCOLOR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.argb(255,255,255,255));


        board=findViewById(R.id.Board);
        red=findViewById(R.id.seekBar1);
        green=findViewById(R.id.seekBar2);
        blue=findViewById(R.id.seekBar3);


        SETCOLOR=findViewById(R.id.button2);
        CLEAR=findViewById(R.id.button);

        CLEAR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                board.clearCanvas();
            }
        });
        SETCOLOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.RED=red.getProgress()*255/100;board.GREEN=green.getProgress()*255/100;board.BLUE=blue.getProgress()*255/100;
            }
        });



    }




}
