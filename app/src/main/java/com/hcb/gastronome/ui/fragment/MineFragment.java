package com.hcb.gastronome.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.RelativeLayout;

import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.adapter.PageAdapter;
import com.hcb.gastronome.ui.base.BaseFragment;
import com.hcb.gastronome.ui.fragment.delicious.DishesFragment;
import com.hcb.gastronome.ui.widget.TabViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class MineFragment extends BaseFragment {
    private PageAdapter adapter;
    private List<BaseFragment>listFragment;
    private List<String >listTitle;
    @Bind(R.id.rl_bar)
    RelativeLayout rlBar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tabViewPager)
    TabViewPager tabViewPager;
    public static MineFragment getInstance() {
        return new MineFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adaptStatusBar(rlBar);
        listFragment=new ArrayList<>();
        listTitle=new ArrayList<>();
        listFragment.add(HomeFragment.getInstance());
        listFragment.add(HomeFragment.getInstance());
        listTitle.add("tab 1");
        listTitle.add("tab 2");
        adapter=new PageAdapter(getChildFragmentManager(),listFragment,listTitle);
        tabViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(tabViewPager);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }
}
