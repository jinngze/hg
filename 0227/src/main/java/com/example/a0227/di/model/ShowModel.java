package com.example.a0227.di.model;

import com.example.a0227.data.utils.RetrofitManager;
import com.google.gson.Gson;

public class ShowModel implements IModel {
    @Override
    public void requestData(String url, final Class clazz, final MyCallBack callBack) {

       RetrofitManager.getRetrofitManager().get(url, new RetrofitManager.HttpListener() {
           @Override
           public void success(String data) {
               Object o = new Gson().fromJson(data, clazz);
               if(callBack != null){
                   callBack.OnSuccess(o);
               }
           }

           @Override
           public void fails(String error) {
                   if(callBack != null){
                       callBack.OnFails(error);
                   }

           }
       });
    }
}
