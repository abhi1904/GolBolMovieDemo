package com.erosnow.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.erosnow.R;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MoviesPagerAdapter sectionsPagerAdapter = new MoviesPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);

    }

    @Override
    public void onBackPressed() {

        if(viewPager.getCurrentItem()==1)
        {
            viewPager.setCurrentItem(0);
        }
        else
        {
            super.onBackPressed();

        }
    }
}