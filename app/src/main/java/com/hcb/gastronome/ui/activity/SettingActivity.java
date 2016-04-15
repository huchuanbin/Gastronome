package com.hcb.gastronome.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.widget.RelativeLayout;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.bmob._User;
import com.hcb.gastronome.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * Created by huchuanbin on 16/4/15.
 */
public class SettingActivity extends BaseActivity {
    @Bind(R.id.app_bar)
    AppBarLayout barLayout;
    @Bind(R.id.rl_bind_email)
    RelativeLayout bindEmail;
    @Bind(R.id.rl_exit)
    RelativeLayout rlExit;

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        adaptStatusBar(barLayout);
        initToolBar("设置");
    }

    @OnClick(R.id.rl_bind_email)
    public void bindEmail() {
        Intent intent = new Intent(this, BindEmailActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.rl_exit)
    public void exit(){
//        _User.logOut(this);   //清除缓存用户对象
        BmobUser.logOut(this);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
