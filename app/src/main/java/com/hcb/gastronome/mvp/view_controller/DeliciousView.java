package com.hcb.gastronome.mvp.view_controller;

import java.util.List;

/**
 * Created by huchuanbin on 16/3/31.
 */
public interface DeliciousView<T> extends BaseView{
    void loadServerData(boolean loadSuccess, List<T> dataList);
}
