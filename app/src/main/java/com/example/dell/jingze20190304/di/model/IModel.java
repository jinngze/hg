package com.example.dell.jingze20190304.di.model;

import com.example.dell.jingze20190304.di.callback.MyCallBack;

public interface IModel {

    void requestData(String url, Class clazz, MyCallBack callBack);
}
