package com.Alatheer.Projects.laylaky.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elashry on 09/07/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> fragmentList;
    private List<String> titlList;
    public ViewPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        fragmentList = new ArrayList<>();
        titlList = new ArrayList<>();
        this.context = context;
    }
    public void AddFragment(List<Fragment> fragmentList)
    {
        this.fragmentList.addAll(fragmentList);
    }
    public void AddTilte(List<String> titlList)
    {
        this.titlList.addAll(titlList);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlList.get(position);
    }
}
