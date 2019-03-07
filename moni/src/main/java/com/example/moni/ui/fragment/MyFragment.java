package com.example.moni.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moni.R;
import com.example.moni.di.view.CustomFlowLayout;
import com.example.moni.ui.activity.TuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyFragment extends Fragment {

    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.cfl_search)
    CustomFlowLayout cflSearch;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);

        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //TODO:将搜索记录读取出来，展示
    }

    private void initView() {
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = new TextView(getActivity());
                textView.setTextColor(Color.BLUE);
                textView.setText(editSearch.getText());
                textView.setBackgroundResource(R.drawable.search_history_bg);
                cflSearch.addView(textView);

                Intent intent = new Intent(getActivity(), TuActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
