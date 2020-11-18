package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

class Newsadapter extends RecyclerView.Adapter<Newsadapter.MyViewHolder> {
private Context context;
private List<Article> articles;

    public Newsadapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.reclayout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
Article article=articles.get(position);
holder.newshead.setText(article.getTitle());
holder.content.setText(article.getContent());
        Glide.with(context).load(article.getUrlToImage()).into(holder.newsImage);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,WebViewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           intent.putExtra("url",article.getUrl());
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView newsImage;
        public TextView newshead;
        public TextView content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            newshead=itemView.findViewById(R.id.newshead);
            newsImage=itemView.findViewById(R.id.imageView);
            content=itemView.findViewById(R.id.content);
        }
    }
}
