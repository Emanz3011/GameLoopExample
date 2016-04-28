package com.example.zacc.gameloopexample;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    float fps = 10f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create New Thread
        new Thread() {
            @Override
            public void run() {
                final PseudoTimer pT = new PseudoTimer();
                while(true){
                    pT.Update();
                    if (pT.frameReady(fps)) {
                        new PseudoTimer().new UIThreadCommand() {
                            @Override
                            public void runCommand() {
                                System.err.println("pT ACTUAL FPS [" + pT.getActualFPS() + "]");
                            }
                        }.start(MainActivity.this);
                    }
                    ;
                }
            }
        }.start();

    }
}