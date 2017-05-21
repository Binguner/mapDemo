package com.example.nenguou.myofo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.example.nenguou.myofo.PageAdapter.PageFragment;
import com.example.nenguou.myofo.PageAdapter.PageFragment2;
import com.example.nenguou.myofo.PageAdapter.SimpleFragmentPagerAdapter;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {

    private ImageView imageView_back;
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private AutoCompleteTextView autoCompleteTextView;
    ArrayList<Fragment> views = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initId();

        PageFragment tab01 = new PageFragment();
        PageFragment2 tab02 = new PageFragment2();



        views.add(tab01);
        views.add(tab02);

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(),this,views);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    private void initId() {
        imageView_back = (ImageView) findViewById(R.id.iv_back2);

    }

    public void iv_back(View view){
        finish();
    }
}
