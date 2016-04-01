package com.hcb.gastronome.mvp.view_controller;

import com.hcb.gastronome.mvp.model.delicious.TabData;

import java.util.List;

/**
 * Created by huchuanbin on 16/3/31.
 */
public interface DeliciousView extends BaseView{
    void loadServerData(boolean loadSuccess, TabData tabData);
}
