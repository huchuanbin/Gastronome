package com.hcb.gastronome.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class HomeFragment extends BaseFragment {
    @Bind(R.id.ib_test)
    ImageView imageView;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        imageView.setImageResource(R.mipmap.test);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }
}
