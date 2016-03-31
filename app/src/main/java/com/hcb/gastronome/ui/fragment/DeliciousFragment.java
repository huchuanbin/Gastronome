package com.hcb.gastronome.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.delicious.TabData;
import com.hcb.gastronome.mvp.presenter.DeliciousPresenter;
import com.hcb.gastronome.mvp.view_controller.DeliciousView;
import com.hcb.gastronome.ui.adapter.TabAdapter;
import com.hcb.gastronome.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class DeliciousFragment extends BaseFragment implements DeliciousView<TabData>{
    private List<BaseFragment> listFragment;
    private List<String> listTitle;
    private TabAdapter adapter;
    @Bind(R.id.bar_layout)
    AppBarLayout barLayout;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.vp)
    ViewPager vp;
    @Inject
    DeliciousPresenter deliciousPresenter;


    public static DeliciousFragment getInstance() {
        return new DeliciousFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        initInjector(this).inject(this);
//        deliciousPresenter.setControllerView(this);
        adaptStatusBar(barLayout);
        listFragment = new ArrayList<>();
        listTitle = new ArrayList<>();
        listFragment.add(new HomeFragment());
        listFragment.add(new HomeFragment());
        listFragment.add(new HomeFragment());
        listFragment.add(new HomeFragment());
        listFragment.add(new HomeFragment());
        listFragment.add(new HomeFragment());
        listFragment.add(new HomeFragment());
        listFragment.add(new HomeFragment());
        listTitle.add("tab 1");
        listTitle.add("tab 1");
        listTitle.add("tab 1");
        listTitle.add("tab 1");
        listTitle.add("tab 1");
        listTitle.add("tab 1");
        listTitle.add("tab 1");
        listTitle.add("tab 1");
        adapter = new TabAdapter(getActivity().getSupportFragmentManager(), listFragment, listTitle);
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);
//        tabLayout.addTab(tabLayout.newTab().setText("tab 1"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab 1"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab 1"));
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_delicious;
    }

    @Override
    public void loadServerData(boolean loadSuccess, List<TabData> dataList) {

    }
}
