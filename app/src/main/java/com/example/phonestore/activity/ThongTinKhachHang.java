package com.example.phonestore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.phonestore.R;
import com.google.android.material.textfield.TextInputEditText;

public class ThongTinKhachHang extends AppCompatActivity {
  TextInputEditText edtname,edtsdt,edtdiachi,edtmail;
  Button btnxacnhan,btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinkhachhang);
    }
}