package com.hcb.gastronome.ui.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.adapter.PageAdapter;
import com.hcb.gastronome.ui.base.BaseActivity;
import com.hcb.gastronome.ui.base.BaseFragment;
import com.hcb.gastronome.ui.fragment.user.LoginFragment;
import com.hcb.gastronome.ui.fragment.user.RegisterFragment;
import com.hcb.gastronome.ui.widget.TabViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by huchuanbin on 16/4/2.
 */
public class UserActivity extends BaseActivity {
    private PageAdapter pageAdapter;
    private boolean state;
    private List<BaseFragment> listFragment;
    @Bind(R.id.rlBar)
    RelativeLayout rlBar;
    @Bind(R.id.tabViewPager)
    TabViewPager tabViewPager;
    @Bind(R.id.tvSwitch)
    TextView tvSwitch;

    @Override
    public int getLayout() {
        return R.layout.activity_user;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        adaptStatusBar(rlBar);
        state = false;
        tabViewPager.setPagingEnable(false);
        listFragment = new ArrayList<>();
        listFragment.add(LoginFragment.getInstance());
        listFragment.add(RegisterFragment.getInstance());
        pageAdapter=new PageAdapter(getSupportFragmentManager(),listFragment);
        tabViewPager.setAdapter(pageAdapter);
        tabViewPager.setCurrentItem(0);
    }
    @OnClick(R.id.tvSwitch)
    public void switchState(){
        if (state){
            tabViewPager.setCurrentItem(0,false);
            state=false;
            tvSwitch.setText("注册");
        }
        else {
            tabViewPager.setCurrentItem(1,false);
            state=true;
            tvSwitch.setText("登录");
        }
    }
}
