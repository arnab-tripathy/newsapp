package com.example.newsapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel{
    private MutableLiveData<Integer> page;
    private MutableLiveData<String> cat;

    public MutableLiveData<Integer> getPage(){
        if(page==null){
            page=new MutableLiveData<>();
            page.setValue(0);
        }

        return page;
    }
public void addpage(){
        if(page!=null){
            page.setValue(page.getValue()+1);
        }
}
    public MutableLiveData<String> getCat(){
        if(page==null){
            cat=new MutableLiveData<>();
            cat.setValue("");
        }

        return cat;
    }



}
