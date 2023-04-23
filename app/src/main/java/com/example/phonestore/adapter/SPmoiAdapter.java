package com.example.phonestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonestore.R;
import com.example.phonestore.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SPmoiAdapter extends RecyclerView.Adapter<SPmoiAdapter.Itemviewholder>{
    Context context;
    ArrayList<Sanpham>sparr;

    public SPmoiAdapter(Context context, ArrayList<Sanpham> sparr) {
        this.context = context;
        this.sparr = sparr;
    }

    @NonNull
    @Override
    public Itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphammoinhat,null);
        Itemviewholder itemviewholder=new Itemviewholder(view);
        return itemviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Itemviewholder holder, int position) {
        Sanpham sanpham =sparr.get(position);
        holder.tvtensp.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.tvgiasp.setText("Giá: "
                +decimalFormat.format(sanpham.getGiasanpham()));
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(holder.imgsp);
    }

    @Override
    public int getItemCount() {
        return sparr.size();
    }

    public class Itemviewholder extends RecyclerView.ViewHolder{
       ImageView imgsp;
       TextView tvtensp,tvgiasp;

        public Itemviewholder(@NonNull View itemView) {
            super(itemView);
            imgsp=itemView.findViewById(R.id.imageviewsanphamm);
            tvgiasp=itemView.findViewById(R.id.textviewgiasanphamm);
            tvtensp=itemView.findViewById(R.id.textviewtensanphamm);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
