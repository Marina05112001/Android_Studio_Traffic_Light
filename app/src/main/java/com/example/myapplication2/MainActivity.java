package com.example.myapplication2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private LinearLayout l1, l2, l3;
    private Button b1;
    private boolean start_stop = false;
    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        l1=findViewById(R.id.lamp_1);
        l2=findViewById(R.id.lamp_2);
        l3=findViewById(R.id.lamp_3);
        b1=findViewById(R.id.button1);
    }
public void onClickStart (View view) {
        if(!start_stop){
            b1.setText("Стоп");
            start_stop = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(start_stop){
                        counter++;
                        switch (counter)
                        {
                            case 100:
                            case 1: l1.setBackgroundColor(getResources().getColor(R.color.red));
                                l2.setBackgroundColor(getResources().getColor(R.color.grey));
                                l3.setBackgroundColor(getResources().getColor(R.color.grey));
                                break;
                            case 2: l1.setBackgroundColor(getResources().getColor(R.color.grey));
                                l2.setBackgroundColor(getResources().getColor(R.color.yellow));
                                l3.setBackgroundColor(getResources().getColor(R.color.grey));
                                break;
                            case 3: l1.setBackgroundColor(getResources().getColor(R.color.grey));
                                l2.setBackgroundColor(getResources().getColor(R.color.grey));
                                l3.setBackgroundColor(getResources().getColor(R.color.green));
                                counter = 0;
                                break;
                        }
                        try {
                            Thread.sleep(800);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start();
        }
        else {
            start_stop = false;
            b1.setText("Старт");
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        start_stop = false;
    }
}