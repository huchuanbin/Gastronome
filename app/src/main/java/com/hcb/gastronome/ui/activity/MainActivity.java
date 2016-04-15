package com.hcb.gastronome.ui.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ImageView;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.bmob._User;
import com.hcb.gastronome.ui.adapter.PageAdapter;
import com.hcb.gastronome.ui.base.BaseActivity;
import com.hcb.gastronome.ui.base.BaseFragment;
import com.hcb.gastronome.ui.fragment.CommodityFragment;
import com.hcb.gastronome.ui.fragment.HomeFragment;
import com.hcb.gastronome.ui.fragment.delicious.DeliciousFragment;
import com.hcb.gastronome.ui.fragment.mine.MineFragment;
import com.hcb.gastronome.ui.widget.TabViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity  {

    private static final String PAGER_INDEX = "pager_index";
    public static String userId;

    @Bind(R.id.tabViewPager)
    TabViewPager tabViewPager;
    @Bind({R.id.iv_home, R.id.iv_delicious, R.id.iv_commodity, R.id.iv_mine})
    List<ImageView> ivTabs;
    private int[] brightRes = {R.mipmap.bright_home, R.mipmap.bright_delicious, R.mipmap.bright_commodity, R.mipmap.bright_mine};
    private int[] darkRes = {R.mipmap.dark_home, R.mipmap.dark_delicious, R.mipmap.dark_commodity, R.mipmap.dark_mine};

    private PagerAdapter pagerAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        tabViewPager.setPagingEnable(false);
        tabViewPager.setOffscreenPageLimit(ivTabs.size());
        int index = 0;
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(PAGER_INDEX, 0);
        }
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.getInstance());
        fragments.add(DeliciousFragment.getInstance());
        fragments.add(CommodityFragment.getInstance());
        fragments.add(MineFragment.getInstance());
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), fragments);
        tabViewPager.setAdapter(pagerAdapter);
        changeTabStyle(index);
        BmobUser userData=  _User.getCurrentUser(MainActivity.this);
        if (userData!=null){
            userId=userData.getObjectId();
        }
        else {
            userId="-1";
        }
    }

    private void changeTabStyle(int index) {
        for (int i = 0; i < ivTabs.size(); i++) {
            ivTabs.get(i).setImageResource(darkRes[i]);
        }
        ivTabs.get(index).setImageResource(brightRes[index]);
        tabViewPager.setCurrentItem(index, false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(PAGER_INDEX,tabViewPager.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    @OnClick({ R.id.iv_home, R.id.iv_delicious, R.id.iv_commodity, R.id.iv_mine})
    void clickTabs(View view) {
        switch (view.getId()) {
            case R.id.iv_home:
                changeTabStyle(0);
                break;
            case R.id.iv_delicious:
                changeTabStyle(1);
                break;
            case R.id.iv_commodity:
                changeTabStyle(2);
                break;
            case R.id.iv_mine:
                changeTabStyle(3);
                break;
        }
    }


}
