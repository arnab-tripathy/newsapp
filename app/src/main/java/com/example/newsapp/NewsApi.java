package com.example.newsapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NewsApi {
   private static final String url="https://newsapi.org/v2/";
    private static final String key="1b79b9a73b084b168f7098a802d95674";
public static Postservice postservice=null;
public static Postservice getservice(){
    if(postservice==null){
        Retrofit retrofit
                =new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postservice=retrofit.create(Postservice.class);
    }
    return  postservice;
}
    public interface  Postservice{
        @GET("top-headlines?country=in&apiKey="+key)
        Call<NewsList> getnewslist();
        @GET("top-headlines?country=in&apiKey="+key)
        Call<NewsList> getcatlist(@Query("category") String cat,@Query("page") int page);

    }
}
