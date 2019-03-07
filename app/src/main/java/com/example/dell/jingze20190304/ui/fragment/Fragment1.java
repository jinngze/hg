package com.example.dell.jingze20190304.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dell.jingze20190304.R;
import com.example.dell.jingze20190304.data.bean.Bean;
import com.example.dell.jingze20190304.data.utils.Apis;
import com.example.dell.jingze20190304.di.presenter.ShowPresenter;
import com.example.dell.jingze20190304.di.view.IView;
import com.example.dell.jingze20190304.ui.activity.H5Activity;
import com.example.dell.jingze20190304.ui.adapter.DDAdapter;
import com.example.dell.jingze20190304.ui.adapter.MyAdapter;
import com.example.dell.jingze20190304.ui.adapter.SSAdapter;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Fragment1 extends Fragment implements IView {

    @BindView(R.id.fly)
    FlyBanner fly;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    @BindView(R.id.button1)
    Button button1;
    private Bean bean;
    private ShowPresenter showPresenter;
    private MyAdapter myAdapter;
    private List<Bean.ResultBean.MlssBean.CommodityListBean> list;
    private List<Bean.ResultBean.PzshBean.CommodityListBeanX> list1;
    private List<Bean.ResultBean.RxxpBean.CommodityListBeanXX> list2;
    private DDAdapter ddAdapter;
    private SSAdapter ssAdapter;
    private int ss =0 ;

    private String[] mImagesUrl = {
            "http://172.17.8.100/images/small/banner/cj.png",
            "http://172.17.8.100/images/small/banner/hzp.png",
            "http://172.17.8.100/images/small/banner/lyq.png",
            "http://172.17.8.100/images/small/banner/px.png",
            "http://172.17.8.100/images/small/banner/wy.png"
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        unbinder = ButterKnife.bind(this, view);


        showPresenter = new ShowPresenter(this);
        initView();
        initData();

        initNetBanner();
        return view;


    }


    private void initView() {

        showPresenter = new ShowPresenter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
        //魔力时尚
        myAdapter = new MyAdapter(getActivity(), list);
        recycler.setAdapter(myAdapter);


        //品质生活
        ssAdapter = new SSAdapter(getActivity(),list1);
        recycler.setAdapter(ssAdapter);

        //热销新品
        ddAdapter = new DDAdapter(getActivity(),list2);
        recycler.setAdapter(ddAdapter);


    }

    private void initData() {

        showPresenter.startRequest(Apis.DATASHOW_URL, Bean.class);
    }


    private void initNetBanner() {
        List<String> imagesUrl = new ArrayList<>();

        for (int i = 0; i < mImagesUrl.length; i++) {
            imagesUrl.add(mImagesUrl[i]);
        }
        fly.setImagesUrl(imagesUrl);

        fly.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), H5Activity.class);
                /* startActivity(new Intent(getActivity(),H5Activity.class));*/
                startActivity(intent);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void OnSuccess(Object data) {

        Bean bean = (Bean) data;
        //魔力时尚
        myAdapter.setData(bean.getResult().getMlss().getCommodityList());

        //品质生活
        ssAdapter.setData(bean.getResult().getPzsh().getCommodityList());

        //热销新品
        ddAdapter.setData(bean.getResult().getRxxp().getCommodityList());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showPresenter.onDetach();
    }

    @OnClick(R.id.button1)
    public void onViewClicked(View view) {

      /*  if(ss == 0){


            //魔力时尚
            myAdapter.setData(bean.getResult().getMlss().getCommodityList());
        }

        if(ss == 1){

            //品质生活
            ssAdapter.setData(bean.getResult().getPzsh().getCommodityList());
        }

        if(ss == 2){
            //热销新品
            ddAdapter.setData(bean.getResult().getRxxp().getCommodityList());

        }*/



    }
}
