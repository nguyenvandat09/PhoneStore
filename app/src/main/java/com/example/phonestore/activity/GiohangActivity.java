package com.example.phonestore.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.phonestore.R;
import com.example.phonestore.adapter.GiohangAdapter;
import com.example.phonestore.adapter.congtongtien;
import com.example.phonestore.adapter.trutongtien;
import com.example.phonestore.ultil.CheckConnetion;

import java.text.DecimalFormat;



public class GiohangActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    TextView txtThongbao;
    static TextView txtTongTien;
    Button btnthanhtoan,btntieptucmuahang;
    GiohangAdapter giohangAdapter;
   static long tongtien=0;
    void Anhxa()
    {
        listView = findViewById(R.id.listviewgiohang);
        txtThongbao = findViewById(R.id.textviewthongbao);
        txtTongTien = findViewById(R.id.textviewtongtien);
        btnthanhtoan = findViewById(R.id.buttonthanhtoangiohang);
        btntieptucmuahang = findViewById(R.id.buttontieptucmuahang);
        toolbar = findViewById(R.id.toolbargiohang);
        giohangAdapter = new GiohangAdapter(GiohangActivity.this, MainActivity.manggiohang, new congtongtien() {
            @Override
            public void tongtien(int a) {

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                txtTongTien.setText(decimalFormat.format(tongtien * 2) + " VND");

                UpdateTongTien();
            }
        }, new trutongtien() {
            @Override
            public void tongtienn(int a) {

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                txtTongTien.setText(decimalFormat.format(tongtien / 2) + " VND");
            }
        });

        listView.setAdapter(giohangAdapter);


    }
    void ActionToolBar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//xu ly su kien khi click toolbar
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    void CheckData()
    {
        if(MainActivity.manggiohang.size()<=0)
        {
            giohangAdapter.notifyDataSetChanged();

            AlertDialog.Builder builder = new AlertDialog.Builder(GiohangActivity.this);
            builder.setTitle("Thông báo");
            builder.setIcon(R.drawable.ic_baseline_shopping_cart_checkout_24);
            builder.setMessage("Giỏ Hàng của bạn đang trống !");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int ix) {

                }
            });
            builder.setNegativeButton("Đi mua hàng", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                      Intent intent =new Intent(GiohangActivity.this,MainActivity.class);
                      startActivity(intent);
                }
            });

            builder.show();
        }


    }
    void ClickItemListview()
    {

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GiohangActivity.this);
                builder.setTitle("Thông Báo");
                builder.setMessage("Ban co muon xoa san pham nay khong ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        MainActivity.manggiohang.remove(i+1);//xoa san pham khoi gio hang
                        giohangAdapter.notifyDataSetChanged();

                        UpdateTongTien();
                        tongtien=0;
                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                        txtTongTien.setText(decimalFormat.format(tongtien ) + " VND");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giohangAdapter.isEmpty();
                        UpdateTongTien();
                    }
                });
                builder.show();
                return true;
            }
        });

//
    }


    void ClickButton()
    {
        btntieptucmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size() >0)
                {
                    Intent intent = new Intent(getApplicationContext(), ThongtinActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    CheckConnetion.ShowToastLong(getApplicationContext(),"Giỏ hàng của bạn đang trống");
                }
            }
        });
    }
    public static void UpdateTongTien()
    {

        for(int i = 0;i<MainActivity.manggiohang.size();i++)
        {
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

            txtTongTien.setText(decimalFormat.format(tongtien)+" VND");


    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolBar();
        CheckData();//kiem tra gio hang
        ClickItemListview();
        ClickButton();
        //UpdateTongTien();
    }
}
