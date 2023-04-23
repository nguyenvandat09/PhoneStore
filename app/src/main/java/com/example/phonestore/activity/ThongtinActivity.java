package com.example.phonestore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.phonestore.R;

import java.util.Timer;
import java.util.TimerTask;

public class ThongtinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(ThongtinActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}