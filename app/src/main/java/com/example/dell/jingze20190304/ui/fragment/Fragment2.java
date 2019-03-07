package com.example.dell.jingze20190304.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.jingze20190304.R;
import com.example.dell.jingze20190304.data.bean.Bean;
import com.example.dell.jingze20190304.data.utils.Apis;
import com.example.dell.jingze20190304.di.presenter.ShowPresenter;
import com.example.dell.jingze20190304.di.view.IView;
import com.example.dell.jingze20190304.ui.adapter.DDAdapter;
import com.example.dell.jingze20190304.ui.adapter.MyAdapter;
import com.example.dell.jingze20190304.ui.adapter.SSAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment2 extends Fragment implements IView {

    @BindView(R.id.recycler2)
    RecyclerView recycler2;
    Unbinder unbinder;
    private ShowPresenter showPresenter;
    private List<Bean.ResultBean.PzshBean.CommodityListBeanX> list1;
    private SSAdapter ssAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two, container, false);
        unbinder = ButterKnife.bind(this, view);

        showPresenter = new ShowPresenter(this);
        initView();
        initData();
        return view;
    }


    private void initView() {

        showPresenter = new ShowPresenter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler2.setLayoutManager(linearLayoutManager);


        //品质生活
        ssAdapter = new SSAdapter(getActivity(),list1);
        recycler2.setAdapter(ssAdapter);
    }


    private void initData() {

        showPresenter.startRequest(Apis.DATASHOW_URL, Bean.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void OnSuccess(Object data) {

        Bean bean = (Bean) data;

        //品质生活
        ssAdapter.setData(bean.getResult().getPzsh().getCommodityList());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showPresenter.onDetach();
    }
}
