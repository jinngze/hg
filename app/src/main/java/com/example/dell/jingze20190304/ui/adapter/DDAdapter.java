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

public class DDAdapter extends RecyclerView.Adapter<DDAdapter.SubViewHolder> {

    Context context;

    private List<Bean.ResultBean.RxxpBean.CommodityListBeanXX> listBeans;

    public DDAdapter(Context context, List<Bean.ResultBean.RxxpBean.CommodityListBeanXX> list) {
        this.context = context;
        listBeans = new ArrayList<>();
    }

    public void setData(List<Bean.ResultBean.RxxpBean.CommodityListBeanXX> data) {
        this.listBeans = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item, null);
        SubViewHolder subViewHolder = new SubViewHolder(view);
        return subViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder subViewHolder, int i) {

        subViewHolder.text.setText(listBeans.get(i).getCommodityName());
        String url = listBeans.get(i).getMasterPic();
        subViewHolder.image.setImageURI(url);
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class SubViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        SimpleDraweeView image;
        @BindView(R.id.text)
        TextView text;
        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
