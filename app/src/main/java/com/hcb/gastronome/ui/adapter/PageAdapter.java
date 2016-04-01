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
    List<BaseFragment> listFragment;
    List<String> listTitles;

    public PageAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.listFragment = fragments;
    }

    public PageAdapter(FragmentManager fm, List<BaseFragment> listFragment, List<String> listTitles) {
        super(fm);
        this.listFragment = listFragment;
        this.listTitles = listTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    public void setTabTitles(List<String> tabTitles) {
        this.listTitles = tabTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (listTitles != null && listTitles.size() > 0)
            return listTitles.get(position);
        return null;
    }
}
