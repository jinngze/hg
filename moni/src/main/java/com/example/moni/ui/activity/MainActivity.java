package com.example.moni.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.moni.R;
import com.example.moni.ui.fragment.DetailFragment;
import com.example.moni.ui.fragment.MyFragment;
import com.example.moni.ui.fragment.ShouFragment;

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
    @BindView(R.id.group)
    RadioGroup group;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentList = new ArrayList<>();
        fragmentList.add(new ShouFragment());
        fragmentList.add(new DetailFragment());
        fragmentList.add(new MyFragment());

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });


        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                switch (i){
                    case 0:
                        group.check(R.id.button1);
                        break;
                    case 1:
                        group.check(R.id.button2);
                        break;
                    case 2:
                        group.check(R.id.button3);
                        break;
                        default:
                            break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

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
                          default:
                          break;

                  }
              }
          });


    }
}
