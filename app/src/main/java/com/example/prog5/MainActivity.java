package com.example.prog5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "thread";

    Handler mainHandler = new Handler();

    int count=0;

    boolean running = false;

    Button startBtn,stopBtn;
    TextView counterVal;

    void startThread(){
        NewThread nObj = new NewThread();
        nObj.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        counterVal = findViewById(R.id.counterValue);

        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                count=0;
                running=true;
                startThread();
            }
        });


        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                running=false;
            }
        });

    }

    class NewThread extends Thread{
        @Override
        public void run() {

            while(running){

                count++;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            counterVal.setText(String.valueOf(count));
                        }
                    });

                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}