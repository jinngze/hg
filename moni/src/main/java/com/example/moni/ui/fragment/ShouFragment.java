package com.example.moni.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moni.R;
import com.example.moni.data.bean.NightBean;
import com.example.moni.data.bean.ZhanBean;
import com.example.moni.data.utils.Apis;
import com.example.moni.di.presenter.ShowPresenter;
import com.example.moni.di.view.IView;
import com.example.moni.ui.activity.ZXingActivity;
import com.example.moni.ui.adapter.NightAdapter;
import com.example.moni.ui.adapter.ZhanAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShouFragment extends Fragment implements IView {


    @BindView(R.id.zxing)
    TextView zxing;
    @BindView(R.id.hand)
    RecyclerView hand;
    @BindView(R.id.footer)
    RecyclerView footer;
    Unbinder unbinder;
    @BindView(R.id.qiehuan)
    ImageView qiehuan;
    private List<NightBean.DataBean> list1;
    private List<ZhanBean.DataBean> list2;
    private NightBean nightBean;
    ShowPresenter showPresenter;
    Context context;
    private NightAdapter nightAdapter;
    private ZhanAdapter zhanAdapter;
    private ZhanBean zhanBean;
    private Boolean p = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shou, container, false);
        unbinder = ButterKnife.bind(this, view);
        showPresenter = new ShowPresenter(this);

        zxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ZXingActivity.class);
                startActivity(intent);

            }
        });

      /* rela_falied.setVisibility(View.GONE);
                rela_success.setVisibility(View.VISIBLE);*/

        qiehuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(p==true){
                    footer.setLayoutManager(new LinearLayoutManager(getActivity()));

                    p=false;
                }else {
                    hand.setLayoutManager(new GridLayoutManager(getActivity(),4));
                    p=true;
                }
            }
        });
        initView();
        getData();
       /* con.setVisibility(View.VISIBLE);
        rela_falied.setVisibility(View.GONE);
        rela_success.setVisibility(View.GONE);*/
        return view;
    }


    private void initView() {
        showPresenter = new ShowPresenter(this);
        //头部
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
        hand.setLayoutManager(gridLayoutManager);
        nightAdapter = new NightAdapter(getActivity(), list1);
        hand.setAdapter(nightAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        footer.setLayoutManager(linearLayoutManager);
        zhanAdapter = new ZhanAdapter(getActivity(), list2);
        footer.setAdapter(zhanAdapter);

    }


    private void getData() {

        showPresenter.startRequest(Apis.NIGHT_URL, NightBean.class);
        showPresenter.startRequest(Apis.ZHU_URL, ZhanBean.class);

    }


    @Override
    public void OnSuccess(Object data) {


        if (data instanceof NightBean) {
            NightBean nightBean = (NightBean) data;

            nightAdapter.setData(nightBean.getData());
        } else if (data instanceof ZhanBean) {
            ZhanBean zhanBean = (ZhanBean) data;

            zhanAdapter.setData(zhanBean.getData());
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
