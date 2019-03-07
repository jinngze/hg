package com.example.moni.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.moni.R;
import com.example.moni.data.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.SubViewHolder> {

    private Context mContext;
    List<ShopBean.DataBean> mList = new ArrayList<>();



    public ShopAdapter(Context context) {
        this.mContext = context;
    }

    public void setList(List<ShopBean.DataBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(mContext, R.layout.shop_store, null);
        SubViewHolder subViewHolder = new SubViewHolder(view);
        return subViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SubViewHolder subViewHolder, final int i) {

        subViewHolder.tvShop.setText(mList.get(i).getSellerName());
        final ProductsAdapter productsAdapter = new ProductsAdapter(mContext, mList.get(i).getList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        subViewHolder.recyclerShop.setLayoutManager(linearLayoutManager);

        subViewHolder.recyclerShop.setAdapter(productsAdapter);

        subViewHolder.checkShop.setChecked(mList.get(i).isCheck());

        productsAdapter.setListener(new ProductsAdapter.ShopCallBackListener() {
            @Override
            public void callBack() {
                if(mShopCallBackListener != null){
                    mShopCallBackListener.callBack(mList);
                }
             List<ShopBean.DataBean.ListBean> listBeans = mList.get(i).getList();

                boolean  isAllChecked = true;

                for (ShopBean.DataBean.ListBean bean : listBeans){

                    if(!bean.isCheck()){
                        isAllChecked = false;
                        break;
                    }
                }

                subViewHolder.checkShop.setChecked(isAllChecked);
                mList.get(i).setCheck(isAllChecked);
            }
        });

         subViewHolder.checkShop.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 mList.get(i).setCheck(subViewHolder.checkShop.isChecked());
                 productsAdapter.selectRemoveAll(subViewHolder.checkShop.isChecked());
             }
         });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class SubViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.check_shop)
        CheckBox checkShop;
        @BindView(R.id.tv_shop)
        TextView tvShop;
        @BindView(R.id.recycler_shop)
        RecyclerView recyclerShop;
        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private ShopCallBackListener mShopCallBackListener;


    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener{

        void callBack(List<ShopBean.DataBean> list);
    }
}
