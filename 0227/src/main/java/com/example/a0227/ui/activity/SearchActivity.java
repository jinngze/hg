package com.example.a0227.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a0227.R;
import com.example.a0227.di.view.CustomFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.cfl_search)
    CustomFlowLayout cflSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO:将搜索记录读取出来，展示
    }

    private void initView() {
                ivSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView textView = new TextView(SearchActivity.this);
                        textView.setTextColor(Color.BLUE);
                        textView.setText(editSearch.getText());
                        textView.setBackgroundResource(R.drawable.search_history_bg);
                        cflSearch.addView(textView);

                        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                });

    }
}
