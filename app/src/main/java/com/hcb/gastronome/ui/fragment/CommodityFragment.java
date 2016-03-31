package com.hcb.gastronome.ui.fragment;

import android.os.Bundle;

import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.base.BaseFragment;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class CommodityFragment extends BaseFragment{
    public static CommodityFragment getInstance(){
        return new CommodityFragment();
    }
    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }
}
