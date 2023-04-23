package com.example.phonestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonestore.R;
import com.example.phonestore.ultil.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;




public class GiohangAdapter extends BaseAdapter {
    int sl=0;
    Context context;
    ArrayList<Giohang> giohangArrayList;
    congtongtien tt;
    trutongtien ttt;

    public GiohangAdapter(Context context, ArrayList<Giohang> giohangArrayList,congtongtien tt,trutongtien ttt) {
        this.context = context;
        this.giohangArrayList = giohangArrayList;
        this.tt=tt;
        this.ttt=ttt;
    }

    @Override
    public int getCount() {
        return giohangArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return giohangArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GHViewHolder viewHolder = null;
        if(view==null)
        {
            viewHolder = new GHViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_giohang,null);
            viewHolder.txttengiohang = view.findViewById(R.id.textviewgiohang);
            viewHolder.txtgiagiohang = view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang = view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnGiaTri = view.findViewById(R.id.buttonvalues);
            viewHolder.btnTru = view.findViewById(R.id.buttonminus);
            viewHolder.btnCong = view.findViewById(R.id.buttonplus);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (GHViewHolder)view.getTag();
        }
        Giohang giohang = (Giohang)getItem(i);
        viewHolder.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp())+" VND");
        Picasso.get().load(giohang.getHinhsp())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(viewHolder.imggiohang);
        viewHolder.btnGiaTri.setText(Integer.toString(giohang.getSoluongsp()));
        GHViewHolder finalViewHolder = viewHolder;

        GHViewHolder finalViewHolder1 = viewHolder;
        viewHolder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             sl++;
             finalViewHolder.btnGiaTri.setText(Integer.toString(giohang.getSoluongsp()+sl));
             tt.tongtien(i);
            }
        });
        viewHolder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             sl--;
                finalViewHolder1.btnGiaTri.setText(Integer.toString(giohang.getSoluongsp()+sl));
                ttt.tongtienn(i);
            }
        });
        return view;
    }
    public class GHViewHolder {
        TextView txttengiohang,txtgiagiohang;
        ImageView imggiohang;
        Button btnTru,btnCong,btnGiaTri;

    }
}
