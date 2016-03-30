package com.hcb.gastronome.di.modules;

import com.trello.rxlifecycle.FragmentLifecycleProvider;

import dagger.Module;

/**
 * Created by huchuanbin on 16/3/29.
 */
@Module
public class FragmentModule {
    private FragmentLifecycleProvider fragmentLifecycleProvider;
    public FragmentModule(FragmentLifecycleProvider fragmentLifecycleProvider){
        this.fragmentLifecycleProvider=fragmentLifecycleProvider;
    }
}
