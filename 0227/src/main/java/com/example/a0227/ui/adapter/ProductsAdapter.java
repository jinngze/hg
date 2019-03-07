package com.example.a0227.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.a0227.R;
import com.example.a0227.data.bean.ShopBean;
import com.example.a0227.di.view.ConView;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MViewHolder> {

      private Context  mContext;
      private List<ShopBean.DataBean.ListBean> mList =new ArrayList<>();


    public ProductsAdapter(Context context, List<ShopBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view =View.inflate(mContext,R.layout.shop_goods,null);
        MViewHolder mViewHolder = new MViewHolder(view);
        return mViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder mViewHolder, final int i) {
        String url = mList.get(i).getImages().split("\\|")[0].replace("https","http");
        Glide.with(mContext).load(url).into(mViewHolder.mImage);

        mViewHolder.mTitle.setText(mList.get(i).getTitle());
        mViewHolder.mPrice.setText(mList.get(i).getPrice()+"");

        mViewHolder.mCheckBox.setChecked(mList.get(i).isCheck());

        mViewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mList.get(i).setCheck(b);

                if(mShopCallBackListener != null){
                    mShopCallBackListener.callBack();
                }
            }
        });

        mViewHolder.mConView.setData(this,mList,i);
        mViewHolder.mConView.setListener(new ConView.CallBackListener() {
            @Override
            public void callBack() {
                if(mShopCallBackListener != null){
                    mShopCallBackListener.callBack();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }




    public class MViewHolder extends RecyclerView.ViewHolder {

               ConView mConView;
               TextView mTitle,mPrice;
               ImageView mImage;
               CheckBox mCheckBox;

        public MViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.iv_product);
            mTitle = itemView.findViewById(R.id.tv_product_title);
            mPrice = itemView.findViewById(R.id.tv_product_price);
            mCheckBox = itemView.findViewById(R.id.check_product);
            mConView = itemView.findViewById(R.id.custom_product_counter);


        }
    }


    public void selectRemoveAll(boolean checked) {

        for (ShopBean.DataBean.ListBean listBean : mList){
            listBean.setCheck(checked);
        }

        notifyDataSetChanged();
    }


    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener{

        void callBack();


    }

}
