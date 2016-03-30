package com.hcb.gastronome.di.component;

import android.content.Context;

import com.hcb.gastronome.AppConfig;
import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.di.modules.ApiModule;
import com.hcb.gastronome.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by huchuanbin on 16/3/29.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    void inject(AppConfig appConfig);

    @ContextLevel(ContextLevel.APPLICATION)
    Context getContext();
}
