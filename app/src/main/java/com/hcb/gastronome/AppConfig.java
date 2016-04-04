package com.hcb.gastronome;

import android.accounts.AccountManager;
import android.app.Application;

import com.hcb.gastronome.di.component.ApplicationComponent;
import com.hcb.gastronome.di.component.DaggerApplicationComponent;
import com.hcb.gastronome.di.modules.ApiModule;
import com.hcb.gastronome.di.modules.ApplicationModule;
import com.thinkland.sdk.android.JuheSDKInitializer;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by Corbin on 2016/3/21.
 */
public class AppConfig extends Application {
    private static AppConfig config;
    public ApplicationComponent applicationComponent;

    public static AppConfig getInstance() {
        return config;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        config=this;
        initComponent();
        JuheSDKInitializer.initialize(getApplicationContext());
        Bmob.initialize(this,"e1752dd83322d79679fc888f6e52709e");
    }

    private void initComponent() {

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule())
                .build();
        applicationComponent.inject(this);

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
