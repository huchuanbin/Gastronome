package com.hcb.gastronome.di.modules;


import android.accounts.AccountManager;
import android.content.Context;

import com.hcb.gastronome.AppConfig;
import com.hcb.gastronome.di.ContextLevel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huchuanbin on 16/3/29.
 */
@Module
public class ApplicationModule {
    private AppConfig appConfig;

    public ApplicationModule(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Provides
    @Singleton
    @ContextLevel(ContextLevel.APPLICATION)
    public Context provideContext() {
        return appConfig.getApplicationContext();
    }

//    @Provides
//    @Singleton
//    public AccountManager provideAccountManager() {
//        return new AccountManager(appConfig);
//    }
}
