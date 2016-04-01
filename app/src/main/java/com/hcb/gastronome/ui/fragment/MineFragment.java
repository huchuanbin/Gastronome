package com.hcb.gastronome.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.adapter.PageAdapter;
import com.hcb.gastronome.ui.base.BaseFragment;

import java.util.List;

import butterknife.Bind;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class MineFragment extends BaseFragment {
    private PageAdapter mainAdapter;
    private List<Fragment>listFragment;
    private List<String >listTitle;
    @Bind(R.id.rl_bar)
    RelativeLayout rlBar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    public static MineFragment getInstance() {
        return new MineFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adaptStatusBar(rlBar);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }
}
