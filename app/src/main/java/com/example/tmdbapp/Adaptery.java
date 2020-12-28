package com.example.tmdbapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {

    private Context mContext;
    private List<MovieModelClass> mData;
    private RecyclerViewClickListener listener;

    public Adaptery(Context mContext, List<MovieModelClass> mData, RecyclerViewClickListener listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.movie_item, parent, false);


        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Adaptery.MyViewHolder holder, int position) {
        holder.id.setText(mData.get(position).getId());
        holder.name.setText(mData.get(position).getName());

        //Using Glide library to display image.

        //{LINK}https://image.tmdb.org/t/p/w500/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg
        Glide.with(mContext)
                .load("https://image.tmdb.org/t/p/w500"+mData.get(position).getImg())
                .into(holder.img);

        
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView id;
        TextView name;
        ImageView img;


        public MyViewHolder(final View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_txt);
            name = itemView.findViewById(R.id.name_txt);
            img = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View itemView) {
            listener.OnClick(itemView, getAdapterPosition());
        }
    }
    public interface RecyclerViewClickListener{
        void OnClick(View itemView, int position);
    }
}
