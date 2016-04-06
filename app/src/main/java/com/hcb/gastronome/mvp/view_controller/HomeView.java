package com.hcb.gastronome.mvp.view_controller;

import com.hcb.gastronome.mvp.model.home.HomeData;

/**
 * Created by huchuanbin on 16/4/5.
 */
public interface HomeView extends BaseView {
    void result(boolean success, HomeData homeData);
}
