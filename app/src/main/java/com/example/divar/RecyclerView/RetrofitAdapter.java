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

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<ModelRecycler> dataModelArrayList;

    public RetrofitAdapter(ArrayList<ModelRecycler> dataModelArrayList, Context context) {
        inflater = LayoutInflater.from(context);
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.retro_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ModelRecycler modelRecycler = dataModelArrayList.get(position);

        Picasso.get().load(modelRecycler.getAvatar()).into(holder.avatar);
        holder.title.setText(modelRecycler.getTitle());
        holder.price.setText(modelRecycler.getPrice());
        holder.time.setText(modelRecycler.getTime());
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
