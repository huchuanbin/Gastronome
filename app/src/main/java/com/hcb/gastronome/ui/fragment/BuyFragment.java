package com.hcb.gastronome.ui.fragment;

import android.os.Bundle;

import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.base.BaseFragment;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class BuyFragment extends BaseFragment{
    public static BuyFragment getInstance(){
        return new BuyFragment();
    }
    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }
}
