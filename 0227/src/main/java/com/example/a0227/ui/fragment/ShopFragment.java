package com.example.a0227.ui.fragment;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a0227.R;
import com.example.a0227.data.bean.ShopBean;
import com.example.a0227.ui.adapter.ShopAdapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShopFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.all_price)
    TextView allPrice;
    @BindView(R.id.summit)
    TextView summit;
    Unbinder unbinder;
    ShopBean  shopBean;
    private ShopAdapter mShopAdapter;
    private List<ShopBean.DataBean> mList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shop_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        String str = getFromAssets("cart.json");
        shopBean = new Gson().fromJson(str, ShopBean.class);
        initView();
        return view;
    }


    //創建一個方法getFromAssets 参数是fileName
    private String getFromAssets(String fileName) {
        try {
            /*
             * InputStreamReader流的作用，实现从字节流到字符流的转换
             * AssetManager类，这个类叫管理资产类,这个类为 访问当前应用程序的资产文件提供了入口。
             * */
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName), "GBK");
            AssetManager assets = getResources().getAssets();
            BufferedReader bufReader = new BufferedReader(inputReader);
            /*
             * line 每一行读出来的数据， alldata 全部的数据
             * */
            String line = "", alldata = "";
            //按行读取
            while ((line = bufReader.readLine()) != null) {
                alldata += line;
            }
            //成功 以后 返回全部数据
            return alldata;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //失败的时候返回一个空的字符串
        return "失败的时候返回一个空的字符串";
    }



    private void initView() {



     checkbox.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       recycle.setLayoutManager(linearLayoutManager);

        mShopAdapter = new ShopAdapter(getActivity());
        mList = shopBean.getData();
        mShopAdapter.setList(mList);
        recycle.setAdapter(mShopAdapter);

        mShopAdapter.setListener(new ShopAdapter.ShopCallBackListener() {
            @Override
            public void callBack(List<ShopBean.DataBean> list) {



                double totalPrice = 0;


                int num = 0;

                int totalNum = 0;
                for (int a = 0; a<list.size(); a++){

                    List<ShopBean.DataBean.ListBean> listAll = list.get(a).getList();
                    for (int i = 0; i<listAll.size(); i++){
                        totalNum = totalNum + listAll.get(i).getNum();

                        if(listAll.get(i).isCheck()) {
                            totalPrice = totalPrice + (listAll.get(i).getPrice() * listAll.get(i).getNum());

                            num = num + listAll.get(i).getNum();
                        }
                    }
                }

                if(num < totalNum) {
                    //不是全部选中
                   checkbox.setChecked(false);
                }else{
                    //是全部选中
                    checkbox.setChecked(true);
                }
               allPrice.setText("合计:"+ totalPrice);
                summit.setText("去结算("+ num +")");
            }
        });
    }




    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.checkbox:
                checkSeller(checkbox.isChecked());
                mShopAdapter.notifyDataSetChanged();
                break;
            default:
        }
    }


    //修改选中状态，获取价格和数量

    private void checkSeller(boolean checked) {

        double totalPrice = 0;
        int num = 0;
        for (int a = 0; a<mList.size(); a++) {
            //遍历商家，改变状态
            ShopBean.DataBean dataBean = mList.get(a);
            dataBean.setCheck(checked);

            List<ShopBean.DataBean.ListBean> listAll = mList.get(a).getList();
            for (int i = 0; i<listAll.size(); i++){
                //遍历商品，改变状态
                listAll.get(i).setCheck(checked);
                totalPrice = totalPrice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                num = num + listAll.get(i).getNum();
            }
        }

        if(checked) {
          allPrice.setText("合计:" + totalPrice);
            summit.setText("去结算（"+ num +")");
        }else{
           allPrice.setText("合计: 0.00");
            summit.setText("去结算(0)");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}


