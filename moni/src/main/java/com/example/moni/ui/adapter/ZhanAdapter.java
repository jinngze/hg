package com.example.moni.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moni.R;
import com.example.moni.data.bean.ZhanBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhanAdapter extends RecyclerView.Adapter<ZhanAdapter.SubViewHolder> {


    public Context mContext;

    private List<ZhanBean.DataBean> mList = new ArrayList<>();


    public ZhanAdapter(Context mContext, List<ZhanBean.DataBean> list) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setData(List<ZhanBean.DataBean> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(mContext, R.layout.zhan_item, null);
        SubViewHolder subViewHolder = new SubViewHolder(view);
        return subViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder subViewHolder, int i) {

        subViewHolder.tt1.setText(mList.get(i).getTitle());
        String url = mList.get(i).getIcon();
        subViewHolder.image1.setImageURI(url);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class SubViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image1)
        SimpleDraweeView image1;
        @BindView(R.id.tt1)
        TextView tt1;

        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
