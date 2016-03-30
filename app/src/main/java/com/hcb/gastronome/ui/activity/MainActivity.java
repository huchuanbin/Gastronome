package com.hcb.gastronome.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.PagerAdapter;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.adapter.PageAdapter;
import com.hcb.gastronome.ui.base.BaseActivity;
import com.hcb.gastronome.ui.base.BaseFragment;
import com.hcb.gastronome.ui.fragment.BuyFragment;
import com.hcb.gastronome.ui.fragment.GastronomyFragment;
import com.hcb.gastronome.ui.fragment.HomeFragment;
import com.hcb.gastronome.ui.fragment.MineFragment;
import com.hcb.gastronome.ui.widget.TabViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String PAGER_INDEX = "pager_index";

    @Bind(R.id.tabViewPager)
    TabViewPager tabViewPager;
    @Bind({R.id.iv_home, R.id.iv_gastronomy, R.id.iv_buy, R.id.iv_mine})
    List<ImageView> ivTabs;
    private int[] brightRes = {R.mipmap.brighthome, R.mipmap.brightsquare, R.mipmap.brightfind, R.mipmap.brightmine};
    private int[] darkRes = {R.mipmap.darkhome, R.mipmap.darksquare, R.mipmap.darkfind, R.mipmap.darkmine};

    private PagerAdapter pagerAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        tabViewPager.setPagingEnable(true);
        tabViewPager.setOffscreenPageLimit(ivTabs.size());
        int index = 0;
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(PAGER_INDEX, 0);
        }
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.getInstance());
        fragments.add(GastronomyFragment.getInstance());
        fragments.add(BuyFragment.getInstance());
        fragments.add(MineFragment.getInstance());
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), fragments);
        tabViewPager.setAdapter(pagerAdapter);
        changeTabStyle(index);
    }

    private void changeTabStyle(int index) {
        for (int i = 0; i < ivTabs.size(); i++) {
            ivTabs.get(i).setImageResource(darkRes[i]);
        }
        ivTabs.get(index).setImageResource(brightRes[index]);
        tabViewPager.setCurrentItem(index, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
    @OnClick({ R.id.iv_home, R.id.iv_gastronomy, R.id.iv_buy, R.id.iv_mine})
    void clickTabs(View view) {
        switch (view.getId()) {
            case R.id.iv_home:
                changeTabStyle(0);
                break;
            case R.id.iv_gastronomy:
                changeTabStyle(1);
                break;
            case R.id.iv_buy:
                changeTabStyle(2);
                break;
            case R.id.iv_mine:
                changeTabStyle(3);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

}
