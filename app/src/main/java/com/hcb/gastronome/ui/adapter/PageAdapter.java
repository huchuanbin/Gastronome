package com.hcb.gastronome.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hcb.gastronome.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class PageAdapter extends FragmentPagerAdapter {
    List<BaseFragment> fragments;
    List<String> tabTitles;

    public PageAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void setTabTitles(List<String> tabTitles) {
        this.tabTitles = tabTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (tabTitles != null && tabTitles.size() > 0)
            return tabTitles.get(position);
        return null;
    }
}
