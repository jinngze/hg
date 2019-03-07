package com.example.moni.di.presenter;

import com.example.moni.di.model.IModel;
import com.example.moni.di.model.MyCallBack;
import com.example.moni.di.model.ShowModel;
import com.example.moni.di.view.IView;

public class ShowPresenter implements IPresenter {


    private IModel iModel;
    private IView iView;

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
