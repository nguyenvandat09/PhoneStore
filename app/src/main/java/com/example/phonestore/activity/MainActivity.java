package com.example.phonestore.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.phonestore.R;
import com.example.phonestore.adapter.LoaispAdapter;
import com.example.phonestore.adapter.RecyclerItemClickListener;
import com.example.phonestore.adapter.SPmoiAdapter;
import com.example.phonestore.model.Loaisp;
import com.example.phonestore.model.Sanpham;
import com.example.phonestore.ultil.CheckConnetion;
import com.example.phonestore.ultil.Giohang;
import com.example.phonestore.ultil.server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    public static ArrayList<Giohang> manggiohang;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    ArrayList<Sanpham> mangsp;
    SPmoiAdapter spAdapter;
    private void getSpmoinhat(){
         RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        //b2.Tao request
        //StringRequest(phuongThuc,DuongDan,ThanhCong,ThatBai)
        //chu ys: truyen tham so cho post
        String path = server.Duongdansanphammoinhat;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                path, new Response.Listener<String>() {
            //neu thanh cong
            @Override
            public void onResponse(String response) {
                int id=0;
                String Tendt="";
                int Giadt = 0;
                String Hinhanhdt="";
                String Mota="";
                int IdsanphamDT=0;
                if(response!=null)
                {
                    try {
                        //chuyen ket qua ve mang cac doi tuong
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++)//doc mang bang vong lap
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);//lay ve doi tuong i
                            id=jsonObject.getInt("id");//lay tung truong du lieu
                            Tendt = jsonObject.getString("tensp");
                            Giadt = jsonObject.getInt("giasp");
                            Hinhanhdt = jsonObject.getString("hinhanhsp");
                            Mota = jsonObject.getString("motasp");
                            IdsanphamDT = jsonObject.getInt("idsanpham");
                            //dua doi tuong vao mamg
                            mangsp.add(new Sanpham(id,Tendt,Giadt,Hinhanhdt,Mota,IdsanphamDT));
                            spAdapter.notifyDataSetChanged();

                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    CheckConnetion.ShowToastLong(getApplicationContext(),"Khong co du lieu");
                }
            }
            //neu that bai
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //b3. xu ly request
        requestQueue.add(stringRequest);
    }

    private void ViewFlipper()
    {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://img.freepik.com/premium-vector/happy-father-s-day-sale-banner-promotion-blue-background-online-shopping-store-with-mobile-credit-cards-shop-elements_62391-268.jpg?w=2000");
        mangquangcao.add("https://img.freepik.com/premium-vector/online-shopping-store-website-mobile-phone-design-smart-business-marketing-concept-horizontal-view-vector-illustration_62391-477.jpg?w=2000");
        mangquangcao.add("https://psrefstuff.lenovo.com/v2/images/classifications/c_tablets.png");
        mangquangcao.add("https://thetekcoffee.com/wp-content/uploads/2022/05/iphone-11-xach-tay-my-ll-a.png");

        for(int i=0;i<mangquangcao.size();i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in);
        Animation animation_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setInAnimation(animation_out);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewlipper);

    }
    private void Anhxa()
    {

//        if(manggiohang!=null)
//        {
//
//        }
//        else
//        {
//            manggiohang = new ArrayList<>();
//        }
        manggiohang = new ArrayList<>();
        toolbar = (Toolbar)findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewlipper);
        recyclerViewmanhinhchinh = (RecyclerView)findViewById(R.id.recycleview);
        navigationView = (NavigationView)findViewById(R.id.navigationview);
        listViewmanhinhchinh = (ListView)findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        mangloaisp = new ArrayList<>();
        mangloaisp.add(0,new Loaisp(0,"Trang chủ","https://illustoon.com/photo/7790.png"));
        loaispAdapter = new LoaispAdapter(mangloaisp,getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaispAdapter);

        mangsp=new ArrayList<>();
        spAdapter=new SPmoiAdapter(getApplicationContext(),mangsp);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        //recyclerViewmanhinhchinh.setBackgroundColor(Color.parseColor("#2196F3"));
        recyclerViewmanhinhchinh.setAdapter(spAdapter);
        recyclerViewmanhinhchinh.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerViewmanhinhchinh ,new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                Intent intent  = new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",mangsp.get(position));
                startActivity(intent);
            }

            @Override public void onLongItemClick(View view, int position) {
                // do whatever
            }
        }));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Anhxa();
        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))
        {
            ActionBar();
            ViewFlipper();
            GetDuLieuLoaisp();
            GetOnClickItemListView();
           // getSpmoinhat();
            TextView tvnew =findViewById(R.id.tvnew);
            tvnew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSpmoinhat();
                }
            });
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        getSpmoinhat();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }






    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.actionbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
                drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
            }
        });
    }
///--------------------
private void GetDuLieuLoaisp()
{
    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
    //JsonArrayRequest(duongDan,thanhcong,thatbai)
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.Duongdanloaisp, new Response.Listener<JSONArray>() {
       //neu thanh cong
        @Override
        public void onResponse(JSONArray response) {
            if(response!=null)//neu ket qua lay ve khac null
            {
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        id=jsonObject.getInt("id");//lay id
                        tenloaisp=jsonObject.getString("tenloaisp");
                        hinhanhloaisp = jsonObject.getString("hinhanhloaisanpham");
                        mangloaisp.add(new Loaisp(id,tenloaisp,hinhanhloaisp));
                        loaispAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //mangloaisp.add(4,new Loaisp(0,"Sale","https://img.favpng.com/0/15/21/sales-logo-discounts-and-allowances-best-practice-png-favpng-ACrL1DqDEv4LYt8DszQ09fmHz.jpg"));
               mangloaisp.add(4,new Loaisp(0,"Liên hệ","https://www.air-it.co.uk/wp-content/uploads/2015/02/kpi-icons-01.png"));
               // mangloaisp.add(5,new Loaisp(0,"Thông tin","http://www.mobilegiving.ca/wp-content/uploads/2015/06/icon_info_lg2.png"));
                mangloaisp.add(5,new Loaisp(0,"Thoát","https://www.pngfind.com/pngs/m/339-3396821_png-file-svg-download-icon-logout-transparent-png.png"));
            }
        }

    }, new Response.ErrorListener() {
        //neu that bai
        @Override
        public void onErrorResponse(VolleyError error) {
            CheckConnetion.ShowToastLong(getApplicationContext(),error.toString());
        }
    });
    requestQueue.add(jsonArrayRequest);
}

    void GetOnClickItemListView(){
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(MainActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            CheckConnetion.ShowToastLong(getApplicationContext(),"Khong co mang");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(MainActivity.this,
                                    DienThoaiActivity.class);
                            intent.putExtra("idloaidsanpham",mangloaisp.get(i).getId());
                            startActivity(intent);
                        }
                        else {
                            CheckConnetion.ShowToastLong(getApplicationContext(),"Khong co mang");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(MainActivity.this, LaptopActivity.class);
                            intent.putExtra("idloaidsanpham",mangloaisp.get(i).getId());
                            startActivity(intent);
                        }
                        else {
                            CheckConnetion.ShowToastLong(getApplicationContext(),"Khong co mang");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3://sale
                        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(getApplicationContext(), SaleActitvity.class);
                            startActivity(intent);
                        }
                        else {
                            CheckConnetion.ShowToastLong(getApplicationContext(),"Khong co mang");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4://lienhe
                        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(getApplicationContext(), LienHeActivity.class);

                            startActivity(intent);
                        }
                        else {
                            CheckConnetion.ShowToastLong(getApplicationContext(),"Khong co mang");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 5:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Thông báo");
                        builder.setIcon(R.drawable.ic_baseline_shopping_cart_checkout_24);
                        builder.setMessage("Bạn có muốn thoát khỏi ứng dụng không");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int ix) {
                                finish();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.show();

                }
            }
        });
    }



}