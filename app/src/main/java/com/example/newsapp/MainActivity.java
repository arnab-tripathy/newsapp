package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;

import com.example.newsapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
Newsadapter newsadapter;
LinearLayoutManager manager;
Boolean isScroll=false;
String category="general";
int page=1;
    Integer currentItems, totalItems, scrollOutItems;
List<Article> articles=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        manager=new LinearLayoutManager(this);
        newsadapter=new Newsadapter(this,articles);
        binding.recyclerview.setAdapter(newsadapter);
        binding.recyclerview.setLayoutManager(manager);
binding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
            Log.d("tag","isscrolltrue");
            isScroll=true;
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        Log.d("tag","onscrolled caled");
        super.onScrolled(recyclerView, dx, dy);
        currentItems = manager.getChildCount();
        totalItems = manager.getItemCount();
        scrollOutItems = manager.findFirstVisibleItemPosition();
        Log.d("tag",currentItems.toString() +""+ totalItems.toString()+ "" + scrollOutItems.toString());
        if(isScroll && (currentItems + scrollOutItems == totalItems))
        {

            isScroll = false;
            Log.d("tag","nw page called");
            getcatdata(category,++page);
        }

    }
});
        getcatdata(category,page);


    }
    public void onclick(View view){

        Log.d("tag",view.getTag().toString());





            getcatdata(view.getTag().toString(),1);


    }

    private void getcatdata(String icategory,int page) {
        Call<NewsList> newsListCall=NewsApi.getservice().getcatlist(icategory,page);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if (response.body() != null) {
                    NewsList list = response.body();
                    List<Article> alist = list.getArticles();

                    articles.addAll(alist);
                  newsadapter.notifyDataSetChanged();
//                    Log.d("TAG", articles.get(0).getContent());
                }
                else{
                    Log.d("tag","null response");
                }
            }
            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("tagO",t.getMessage());
            }
        });

    }



}