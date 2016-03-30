package com.hcb.gastronome.di.modules;

import android.app.Activity;
import android.content.Context;

import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.di.scope.ActivityScope;
import com.trello.rxlifecycle.ActivityLifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huchuanbin on 16/3/29.
 */
@Module
public class ActivityModule {
    private ActivityLifecycleProvider activityLifecycleProvider;

    public ActivityModule(ActivityLifecycleProvider activityLifecycleProvider) {
        this.activityLifecycleProvider = activityLifecycleProvider;
    }

    @Provides
    @ActivityScope
    ActivityLifecycleProvider getActivityLifecycleProvider() {
        return this.activityLifecycleProvider;
    }

    @Provides
    @ActivityScope
    @ContextLevel(ContextLevel.ACTIVITY)
    Context getContext() {
        return (Activity) this.activityLifecycleProvider;
    }
}
