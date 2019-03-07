package com.example.dell.jingze20190304.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.jingze20190304.R;
import com.example.dell.jingze20190304.data.bean.Bean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SSAdapter extends RecyclerView.Adapter<SSAdapter.SubViewHodle> {

    Context context;
    private List<Bean.ResultBean.PzshBean.CommodityListBeanX> listBeans;


    public SSAdapter(Context context, List<Bean.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context = context;
        listBeans = new ArrayList<>();

    }

    public void setData(List<Bean.ResultBean.PzshBean.CommodityListBeanX> data) {
        this.listBeans = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubViewHodle onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(context, R.layout.item, null);
        SubViewHodle subViewHodle1 = new SubViewHodle(view);
        return subViewHodle1;
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHodle subViewHodle, int i) {
        subViewHodle.text.setText(listBeans.get(i).getCommodityName());
        String url = listBeans.get(i).getMasterPic();
        subViewHodle.image.setImageURI(url);
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }


    class SubViewHodle extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        SimpleDraweeView image;
        @BindView(R.id.text)
        TextView text;
        public SubViewHodle(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
