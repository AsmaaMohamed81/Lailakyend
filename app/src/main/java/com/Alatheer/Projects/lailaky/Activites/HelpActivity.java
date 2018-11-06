package com.Alatheer.Projects.lailaky.Activites;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.Alatheer.Projects.lailaky.Adapter.ImageAdapter;
import com.Alatheer.Projects.lailaky.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

public class HelpActivity extends AppCompatActivity {

    DiscreteScrollView discreteScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ViewPager viewPager = (ViewPager) findViewById(R.id.picker);
        ImageAdapter adapter = new ImageAdapter(this);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(adapter.getCount()-1);
    }}
