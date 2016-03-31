package com.hcb.gastronome.ui.fragment;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.base.BaseFragment;

import butterknife.Bind;
import dagger.Module;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class MineFragment extends BaseFragment {
    @Bind(R.id.rl_bar)
    RelativeLayout rlBar;
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
