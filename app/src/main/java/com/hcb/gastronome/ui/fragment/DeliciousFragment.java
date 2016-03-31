package com.hcb.gastronome.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class DeliciousFragment extends BaseFragment {
    @Bind(R.id.bar_layout)
    AppBarLayout barLayout;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.tv_title)
    TextView tvTitle;


    public static DeliciousFragment getInstance() {
        return new DeliciousFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adaptStatusBar(barLayout);
        tabLayout.addTab(tabLayout.newTab().setText("tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("tab 1"));
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_delicious;
    }
}
