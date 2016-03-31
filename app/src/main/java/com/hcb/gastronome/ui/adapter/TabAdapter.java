package com.hcb.gastronome.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.gastronome.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by huchuanbin on 16/3/29.
 */
public class TabAdapter extends FragmentPagerAdapter{
    private List<BaseFragment>listFragment;
    List<String>listTitle;
    public TabAdapter(FragmentManager fm, List<BaseFragment>listFragment, List<String>listTitle) {
        super(fm);
        this.listFragment=listFragment;
        this.listTitle=listTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
}
