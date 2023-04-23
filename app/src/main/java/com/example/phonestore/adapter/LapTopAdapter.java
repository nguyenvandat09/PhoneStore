package com.example.phonestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonestore.R;
import com.example.phonestore.activity.LaptopActivity;
import com.example.phonestore.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LapTopAdapter extends BaseAdapter {
    ArrayList<Sanpham> sanphamArrayList;
    Context context;
    public LapTopAdapter(Context context,ArrayList<Sanpham> arr)
    {
        this.sanphamArrayList=arr;
        this.context=context;
    }
    @Override
    public int getCount() {
        return sanphamArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return sanphamArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolderDT{
        public TextView txttendienthoai,txtgiadienthoai,txtmotadienthoai;
        public ImageView imgdienthoai;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      LapTopAdapter.ViewHolderDT viewHolderDT=null;
        if(view==null)
        {
            viewHolderDT=new LapTopAdapter.ViewHolderDT();
            LayoutInflater inflater=(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.item_laptop,null);
            viewHolderDT.txttendienthoai=view.findViewById(R.id.textviewlaptop);
            viewHolderDT.txtgiadienthoai=view.findViewById(R.id.textviewgialaptop);
          //  viewHolderDT.txtmotadienthoai=view.findViewById(R.id.textviewmotalaptop);
            viewHolderDT.imgdienthoai=view.findViewById(R.id.imageviewlaptop);
            view.setTag(viewHolderDT);
        }
        else
        {
            viewHolderDT=(LapTopAdapter.ViewHolderDT)view.getTag();
        }
        //xu ly du lieu
        Sanpham sanpham=(Sanpham) getItem(i);
        viewHolderDT.txttendienthoai.setText(sanpham.getTensanpham());
        //dinh dang phan gia
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolderDT.txtgiadienthoai.setText("Giá: "
                +decimalFormat.format(sanpham.getGiasanpham()));
        //chi cho hien thi chi tiet 2 dong
        //viewHolderDT.txtmotadienthoai.setMaxLines(2);
       // viewHolderDT.txtmotadienthoai.setText(sanpham.getMotasanpham());
        //lay hinh anh tu mang
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(viewHolderDT.imgdienthoai);
        return view;
    }
}
