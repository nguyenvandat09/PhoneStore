package com.example.phonestore.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phonestore.R;
import com.example.phonestore.model.ThuThu;
import com.example.phonestore.ultil.Controller;

public class LoginActivity extends AppCompatActivity {
    TextView tvKQ,tvdangky;
    EditText txtName,txtPrice;
    Button btnthem;
    CheckBox cbLuuThongTin;
    Context context=this;
    public static String KEY_USERNAME = "tennguoidung";
    public static String KEY_PASSWORD = "matkhau";
    public static String KEY_CHECKSTATUS = "checkstatus";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvKQ = findViewById(R.id.tvghichu1);
        txtName = findViewById(R.id.etUsername);
        txtPrice = findViewById(R.id.etPassword);
        btnthem = findViewById(R.id.btLogin);
        tvdangky=findViewById(R.id.textDangKi);
        cbLuuThongTin=findViewById(R.id.cbLuuThongTin);
        Controller daoController= new Controller(this);;
        tvdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);

            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenTaiKhoan = txtName.getText().toString();
                String matKhau = txtPrice.getText().toString();
                //loginVoley1();
                if (txtName.length()<=0||txtPrice.length()<=0){
                    tvKQ.setText("Bạn chưa nhập đủ thông tin");
                    tvKQ.setTextColor(Color.RED);
                }else if (txtPrice.length()<8){
                    tvKQ.setText("Mật khẩu phải trên 8 ký tự");
                    tvKQ.setTextColor(Color.RED);
                }else {
                    ThuThu thu = daoController.getUserLogin(tenTaiKhoan, matKhau);
                    if (tenTaiKhoan.equals(thu.getTaiKhoan()) && matKhau.equals(thu.getMaKhau())) {
                        luuThongTin();
                        Intent intent=new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        tvKQ.setText("Nhập sai Mật khẩu hoặc Tài khoản");
                        tvKQ.setTextColor(Color.RED);
                        return;
                    }

                }

            }
        });
    }
    private void luuThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("thuVien", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String ten = txtName.getText().toString();
        String pass = txtPrice.getText().toString();
        boolean check = cbLuuThongTin.isChecked();
        if (!check) {
            editor.clear();
        } else {
            editor.putString(KEY_USERNAME, ten);
            editor.putString(KEY_PASSWORD, pass);
            editor.putBoolean(KEY_CHECKSTATUS, check);
        }
        editor.commit();
    }

    private void layThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("thuVien", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean(KEY_CHECKSTATUS, false);
        if (check) {
            String tenNguoiDung = sharedPreferences.getString(KEY_USERNAME, "");
            String matKhau = sharedPreferences.getString(KEY_PASSWORD, "");
            txtName.setText(tenNguoiDung);
            txtPrice.setText(matKhau);
        } else {
            txtName.setText("");
            txtPrice.setText("");
        }
        cbLuuThongTin.setChecked(check);

    }

    @Override
    protected void onResume() {
        super.onResume();
        layThongTin();
    }
    public void loginVoley1() {
        new Connect_sv().login_volley_POST(context,tvKQ,txtName,txtPrice);
   }
}