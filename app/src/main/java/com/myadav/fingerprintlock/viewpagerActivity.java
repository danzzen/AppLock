package com.myadav.fingerprintlock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.myadav.fingerprintlock.Custom.CustomPagerAdapter;

/**
 * Created by lenovo on 28-08-2017.Mohit yadav
 */

public class viewpagerActivity extends AppCompatActivity {
    private ViewPager mviewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_viewpager);
        mviewPager=(ViewPager)findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mviewPager, true);
        mviewPager.setAdapter(new CustomPagerAdapter(this));
    }
}
