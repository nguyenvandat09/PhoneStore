package com.example.phonestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonestore.R;
import com.example.phonestore.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> loaispArrayList;
    Context context;
    public LoaispAdapter(ArrayList<Loaisp> arr,Context context)
    {
        this.loaispArrayList=arr;
        this.context=context;
    }
    @Override
    public int getCount() {
        return loaispArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return loaispArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        //tao view
        if(view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.item_listview_loaisp,null);
            viewHolder.txttenloaisp=view.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp=view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder) view.getTag();
        }
        //gan du lieu
        Loaisp loaisp=(Loaisp) getItem(i);
        viewHolder.txttenloaisp.setText(loaisp.getTenloaisp());
        //doc anh online bang picasso
        Picasso.get().load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(viewHolder.imgloaisp);
        return view;
    }

    public class ViewHolder{
        ImageView imgloaisp;
        TextView txttenloaisp;
    }
}
