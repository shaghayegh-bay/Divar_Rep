package com.example.divar.RecyclerView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.divar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {
    Context context;
    private LayoutInflater inflater;
    private ArrayList<Item> dataModelArrayList;

    public RetrofitAdapter(ArrayList<Item> dataModelArrayList, Context context) {
        this.dataModelArrayList = dataModelArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Item item = dataModelArrayList.get(position);

        Picasso.get().load(item.getItemAvatar()).into(holder.avatar);
        holder.title.setText(item.getItemTitle());
        holder.price.setText(item.getItemPrice());
        holder.time.setText(item.getItemTime());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView avatar;
        public TextView title, price, time;

        public MyViewHolder(View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.img_avatar);
            title = itemView.findViewById(R.id.txt_title);
            price = itemView.findViewById(R.id.txt_price);
            time = itemView.findViewById(R.id.txt_time);

        }
    }
}
