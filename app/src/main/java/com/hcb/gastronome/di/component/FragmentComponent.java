package com.hcb.gastronome.di.component;

import com.hcb.gastronome.di.modules.FragmentModule;
import com.hcb.gastronome.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by huchuanbin on 16/3/29.
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
}
