package com.example.moni.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.moni.R;
import com.example.moni.data.bean.ShopBean;
import com.example.moni.di.view.ConView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MViewHolder> {


    private Context mContext;
    private List<ShopBean.DataBean.ListBean> mList = new ArrayList<>();


    public ProductsAdapter(Context context, List<ShopBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(mContext, R.layout.shop_goods, null);
        MViewHolder mViewHolder = new MViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder mViewHolder, final int i) {
      /*  String url = mList.get(i).getImages().split("\\|")[0].replace("https","http");
        Glide.with(mContext).load(url).into(mViewHolder.mImage);*/

        String url = mList.get(i).getImages();
        if (!url.isEmpty()) {
            String[] split = url.split("\\|");
            if (split != null) {
                for (int j = 0; j < split.length; j++) {
                    String replace = split[j].replace("https", "http");
                    Uri parse = Uri.parse(replace);
                    mViewHolder.ivProduct.setImageURI(parse);
                }
            } else {
                String replace = url.replace("https", "http");
                Uri parse = Uri.parse(replace);
               mViewHolder.ivProduct.setImageURI(parse);
            }
        }

       /* //没有图片切割
        String url = mList.get(i).getImages();
        Uri parse = Uri.parse(replace);
        mViewHolder.ivProduct.setImageURI(parse);*/

        mViewHolder.tvProductTitle.setText(mList.get(i).getTitle());
        mViewHolder.tvProductPrice.setText(mList.get(i).getPrice() + "");

        mViewHolder.checkProduct.setChecked(mList.get(i).isCheck());

        mViewHolder.checkProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mList.get(i).setCheck(b);

                if (mShopCallBackListener != null) {
                    mShopCallBackListener.callBack();
                }
            }
        });

        mViewHolder.customProductCounter.setData(this, mList, i);
        mViewHolder.customProductCounter.setListener(new ConView.CallBackListener() {
            @Override
            public void callBack() {
                if (mShopCallBackListener != null) {
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
        @BindView(R.id.check_product)
        CheckBox checkProduct;
        @BindView(R.id.iv_product)
        SimpleDraweeView ivProduct;
        @BindView(R.id.tv_product_title)
        TextView tvProductTitle;
        @BindView(R.id.tv_product_price)
        TextView tvProductPrice;
        @BindView(R.id.custom_product_counter)
        ConView customProductCounter;

        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);


        }
    }


    public void selectRemoveAll(boolean checked) {

        for (ShopBean.DataBean.ListBean listBean : mList) {
            listBean.setCheck(checked);
        }

        notifyDataSetChanged();
    }


    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {

        void callBack();


    }

}
