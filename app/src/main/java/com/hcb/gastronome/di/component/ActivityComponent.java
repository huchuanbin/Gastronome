package com.hcb.gastronome.di.component;

import com.hcb.gastronome.di.modules.ActivityModule;
import com.hcb.gastronome.di.scope.ActivityScope;
import com.hcb.gastronome.ui.activity.MenuActivity;

import dagger.Component;

/**
 * Created by huchuanbin on 16/3/29.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(MenuActivity menuActivity);
}
