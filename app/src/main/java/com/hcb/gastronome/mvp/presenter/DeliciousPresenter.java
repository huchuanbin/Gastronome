package com.hcb.gastronome.mvp.presenter;

import android.content.Context;

import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.view_controller.DeliciousView;
import com.trello.rxlifecycle.FragmentLifecycleProvider;

import javax.inject.Inject;

/**
 * Created by huchuanbin on 16/3/31.
 */
public class DeliciousPresenter extends BasePresenter<DeliciousView> {
    private Context context;
    private FragmentLifecycleProvider fragmentLifecycleProvider;

    @Inject
    public DeliciousPresenter(@ContextLevel(ContextLevel.FRAGMENT) Context context, FragmentLifecycleProvider fragmentLifecycleProvider) {
        this.context = context;
        this.fragmentLifecycleProvider = fragmentLifecycleProvider;
    }
}
