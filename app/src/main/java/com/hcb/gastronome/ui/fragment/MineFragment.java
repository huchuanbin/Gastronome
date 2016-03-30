package com.hcb.gastronome.ui.fragment;

import android.os.Bundle;

import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.base.BaseFragment;

import dagger.Module;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class MineFragment extends BaseFragment {
    public static MineFragment getInstance() {
        return new MineFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }
}
