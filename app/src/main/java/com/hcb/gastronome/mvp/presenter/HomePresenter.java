package com.hcb.gastronome.mvp.presenter;

import android.content.Context;

import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.view_controller.HomeView;

import javax.inject.Inject;

/**
 * Created by huchuanbin on 16/4/5.
 */
public class HomePresenter extends BasePresenter<HomeView> {
    private boolean loadSuccess;
    private Context context;

    @Inject
    public HomePresenter(@ContextLevel(ContextLevel.FRAGMENT) Context context) {
        this.context = context;
    }
    public void getBannerData(){

    }
}
