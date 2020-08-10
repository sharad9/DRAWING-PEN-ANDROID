package com.example.drawingpen;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;


import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Paper board;
    Spinner spinner;
    ArrayAdapter<String> adapter;
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

        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("10f");
        spinnerArray.add("20f");
        spinnerArray.add("30f");
        spinnerArray.add("40f");
        spinnerArray.add("50f");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    board.width = Float.parseFloat(item.toString());

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });

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
