package com.example.moni.ui.fragment;

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
import android.widget.TextView;

import com.example.moni.R;
import com.example.moni.data.bean.ShopBean;
import com.example.moni.data.utils.Apis;
import com.example.moni.di.presenter.ShowPresenter;
import com.example.moni.di.view.IView;
import com.example.moni.ui.adapter.ShopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailFragment extends Fragment implements IView, View.OnClickListener {

    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.all_price)
    TextView allPrice;
    @BindView(R.id.summit)
    TextView summit;
    Unbinder unbinder;
    private ShowPresenter showPresenter;
    private List<ShopBean.DataBean> mList = new ArrayList<>();
    private ShopAdapter mShopAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        unbinder = ButterKnife.bind(this, view);
        showPresenter = new ShowPresenter(this);

        checkbox.setOnClickListener(this);
        initView();
        getData();
        return view;
    }

    private void initView() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(linearLayoutManager);
        mShopAdapter = new ShopAdapter(getActivity());
        recycle.setAdapter(mShopAdapter);

        mShopAdapter.setListener(new ShopAdapter.ShopCallBackListener() {
            @Override
            public void callBack(List<ShopBean.DataBean> list) {
                //在这里重新遍历已经改状态后的数据，
                // 这里不能break跳出，因为还需要计算后面点击商品的价格和数目，所以必须跑完整个循环

                double totalprice = 0;
                //勾选商品的数量，不是该商品购买的数量
                int num = 0;
                //所有商品总数，和上面的数量做比对，如果两者相等，则说明全选
                int totalNum = 0;
                for (int a = 0; a < list.size(); a++) {
                    //获取商家里商品
                    List<ShopBean.DataBean.ListBean> listAll = list.get(a).getList();
                    for (int i = 0; i < listAll.size(); i++) {
                        totalNum = totalNum + listAll.get(i).getNum();
                        //取选中的状态
                        if (listAll.get(i).isCheck()) {
                            totalprice = totalprice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                            num = num + listAll.get(i).getNum();
                        }
                    }
                }

                if (num < totalNum) {
                    //不是全部选中
                    checkbox.setChecked(false);
                } else {
                    checkbox.setChecked(true);
                }

                summit.setText("合计:" + totalprice);
                allPrice.setText("去结算(" + num + ")");

            }
        });
    }


    private void getData() {
        showPresenter.startRequest(Apis.GOU_URL,ShopBean.class);

    }


    @Override
    public void OnSuccess(Object data) {

        if (data instanceof ShopBean) {
            ShopBean shopBean = (ShopBean) data;
            mList = shopBean.getData();
           if (mList != null) {
                mList.remove(0);
                mShopAdapter.setList(mList);
            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.checkbox:
                checkSeller(checkbox.isChecked());
                mShopAdapter.notifyDataSetChanged();
                break;
            default:
        }

    }

    /**
     * 修改选中状态，获取价格和数量
     */

    private void checkSeller(boolean checked) {

        double totalPrice = 0;
        int num = 0;
        for (int a = 0; a < mList.size(); a++) {
            //遍历商家，改变状态
            ShopBean.DataBean dataBean = mList.get(a);
            dataBean.setCheck(checked);

            List<ShopBean.DataBean.ListBean> listAll = mList.get(a).getList();
            for (int i = 0; i < listAll.size(); i++) {
                //遍历商品，改变状态
                listAll.get(i).setCheck(checked);
                totalPrice = totalPrice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                num = num + listAll.get(i).getNum();
            }
        }
        if (checked) {
            summit.setText("合计:" + totalPrice);
            allPrice.setText("去结算(" + num + ")");
        } else {

            summit.setText("合计: 0.00");
            allPrice.setText("去计算(0)");

        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        showPresenter.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
