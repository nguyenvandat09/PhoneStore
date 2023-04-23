package com.example.phonestore.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phonestore.R;
import com.example.phonestore.model.ThuThu;
import com.example.phonestore.ultil.Controller;

public class RegisterActivity extends AppCompatActivity {
    private Controller mDaoTHuThu;
    TextView tvKQ,tvDangnhap;
    EditText txtPid,txtName,txtPrice,txtDes,nhaplaimk;
    Button btnthem;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvKQ = findViewById(R.id.tvghichu);
        //txtPid = findViewById(R.id.txtPid);
        txtName = findViewById(R.id.etmatkhaucu);
        txtPrice = findViewById(R.id.etmatkhaumoi);
        txtDes = findViewById(R.id.nhaplaimatkhaumoi);
        tvDangnhap=findViewById(R.id.textDangNhapm);
        nhaplaimk=findViewById(R.id.nhaplaimatkhau);
        btnthem=findViewById(R.id.btDangKi);
        mDaoTHuThu = new Controller(this);
        tvDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = txtDes.getText().toString();
                String user = txtName.getText().toString();
                String password = txtPrice.getText().toString();
                String repassword= nhaplaimk.getText().toString();

                if (hoten.isEmpty()||user.isEmpty()||password.isEmpty()||repassword.length()<=0){
                    tvKQ.setText("Bạn chưa nhập đủ thông tin");
                    tvKQ.setTextColor(Color.RED);
                }else if(!password.equalsIgnoreCase(repassword)){
                    tvKQ.setText("Mật khẩu không trùng khớp");
                    tvKQ.setTextColor(Color.RED);
                }else if(password.length()<8){
                    tvKQ.setText("Mật khẩu phải trên 8 ký tự");
                    tvKQ.setTextColor(Color.RED);
                }else {
                    tvKQ.setTextColor(Color.GREEN);

                    ThuThu thutthu = new ThuThu(user, 0, password, hoten);
                    if (mDaoTHuThu.themKind(thutthu) == true) {
                        themVoley1();
                        Toast.makeText(getApplicationContext(), ""+tvKQ, Toast.LENGTH_SHORT).show();
                        tvKQ.setText("Đăng ký thành công");
                    } else {
                        //Toast.makeText(getApplicationContext(), "More failure!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public void themVoley1() {
        new Connect_sv().insert_volley_POST(context,tvKQ,txtName,txtPrice,txtDes);

    }
}