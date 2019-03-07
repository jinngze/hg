package com.example.moni.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moni.R;
import com.example.moni.data.bean.NightBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NightAdapter extends RecyclerView.Adapter<NightAdapter.SubViewHolder> {

    Context mContext;
    List<NightBean.DataBean> mList = new ArrayList<>();



    public NightAdapter(Context mContext, List<NightBean.DataBean> list) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setData(List<NightBean.DataBean> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.jiu_item, null);
        SubViewHolder subViewHolder = new SubViewHolder(view);
        return subViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder subViewHolder, int i) {
        subViewHolder.tt.setText(mList.get(i).getName());
        String url = mList.get(i).getIcon();
        subViewHolder.image.setImageURI(url);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class SubViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        SimpleDraweeView image;
        @BindView(R.id.tt)
        TextView tt;

        public SubViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
