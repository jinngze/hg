package com.example.dell.jingze20190304.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dell.jingze20190304.R;
import com.example.dell.jingze20190304.ui.fragment.Fragment1;
import com.example.dell.jingze20190304.ui.fragment.Fragment2;
import com.example.dell.jingze20190304.ui.fragment.Fragment3;
import com.example.dell.jingze20190304.ui.fragment.Fragment4;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.button1)
    RadioButton button1;
    @BindView(R.id.button2)
    RadioButton button2;
    @BindView(R.id.button3)
    RadioButton button3;
    @BindView(R.id.button4)
    RadioButton button4;
    @BindView(R.id.group)
    RadioGroup group;
    private List fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentList = new ArrayList <>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment4());

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return (Fragment) fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.button1:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.button2:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.button3:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.button4:
                        viewpager.setCurrentItem(3);
                        break;
                }
            }
        });
    }
}
