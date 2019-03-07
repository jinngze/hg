package com.example.a0227.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.a0227.R;
import com.example.a0227.data.bean.ShopBean;


import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MViewHolder> {

    private List<ShopBean.DataBean> mList = new ArrayList<>();
    private Context mContext;

    public ShopAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(mContext,R.layout.shop_store,null);
        MViewHolder mViewHolder = new MViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MViewHolder mViewHolder, final int i) {

        mViewHolder.mSeller.setText(mList.get(i).getSellerName());
        final ProductsAdapter productsAdapter = new ProductsAdapter(mContext,mList.get(i).getList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mViewHolder.mRecyclerView.setLayoutManager(linearLayoutManager);

        mViewHolder.mRecyclerView.setAdapter(productsAdapter);
        mViewHolder.mCheck.setChecked(mList.get(i).isCheck());

        productsAdapter.setListener(new ProductsAdapter.ShopCallBackListener() {
            @Override
            public void callBack() {
                if(mShopCallBackListener != null){
                    mShopCallBackListener.callBack(mList);
                }

                List<ShopBean.DataBean.ListBean> listBeans = mList.get(i).getList();

                boolean isAllChecked =true;
                for (ShopBean.DataBean.ListBean bean : listBeans){

                    if(!bean.isCheck()){
                        isAllChecked = false;
                        break;
                    }
                }


                mViewHolder.mCheck.setChecked(isAllChecked);
                mList.get(i).setCheck(isAllChecked);

            }
        });

        mViewHolder.mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mList.get(i).setCheck(mViewHolder.mCheck.isChecked());
                productsAdapter.selectRemoveAll(mViewHolder.mCheck.isChecked());
            }
        });

    }




    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setList(List<ShopBean.DataBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public class MViewHolder extends RecyclerView.ViewHolder {

        RecyclerView mRecyclerView;
        TextView mSeller;
        CheckBox mCheck;
        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            mSeller = itemView.findViewById(R.id.tv_shop);
            mCheck = itemView.findViewById(R.id.check_shop);
            mRecyclerView = itemView.findViewById(R.id.recycler_shop);

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
