package com.example.phonestore.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phonestore.R;
import com.example.phonestore.ultil.CheckConnetion;

import java.util.Timer;
import java.util.TimerTask;



public class HelloActivity extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        Timer timer = new Timer();
        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))
        {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent=new Intent(HelloActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            },2000);
        }
        else {
            CheckConnetion.ShowToastLong(getApplicationContext(),"khong co mang");
            finish();
        }
    }
}
