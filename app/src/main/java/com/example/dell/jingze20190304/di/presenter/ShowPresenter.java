package com.example.dell.jingze20190304.di.presenter;

import com.example.dell.jingze20190304.di.callback.MyCallBack;
import com.example.dell.jingze20190304.di.model.IModel;
import com.example.dell.jingze20190304.di.model.ShowModel;
import com.example.dell.jingze20190304.di.view.IView;

public class ShowPresenter implements IPresenter {

    IModel iModel;
    IView iView;

    public ShowPresenter(IView iView) {
        this.iView = iView;
        iModel = new ShowModel();
    }


    @Override
    public void startRequest(String url, Class clazz) {

       iModel.requestData(url, clazz, new MyCallBack() {
           @Override
           public void OnSuccess(Object data) {
                 iView.OnSuccess(data);
           }

           @Override
           public void OnFails(String error) {

           }
       });
    }

      public void onDetach(){

        if(iModel != null){

            iModel = null;
        }

        if(iView != null){
            iView = null;
        }
      }
}
