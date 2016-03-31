package com.hcb.gastronome.di.modules;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.di.scope.FragmentScope;
import com.trello.rxlifecycle.FragmentLifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huchuanbin on 16/3/29.
 */
@Module
public class FragmentModule {
    private FragmentLifecycleProvider fragmentLifecycleProvider;
    public FragmentModule(FragmentLifecycleProvider fragmentLifecycleProvider){
        this.fragmentLifecycleProvider=fragmentLifecycleProvider;
    }
    @Provides
    @FragmentScope
    FragmentLifecycleProvider getFragmentLifecycleProvider(){
        return this.fragmentLifecycleProvider;
    }
    @Provides
    @FragmentScope
    @ContextLevel(ContextLevel.FRAGMENT)
    Context getContext(){
        return ((Fragment)fragmentLifecycleProvider).getActivity();
    }

}
