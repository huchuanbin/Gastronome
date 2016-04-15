package com.hcb.gastronome.mvp.view_controller;

import com.hcb.gastronome.mvp.model.bmob.Commondity;

import java.util.List;

/**
 * Created by huchuanbin on 16/4/15.
 */
public interface CommodityView extends BaseView {
    void loadServerData(List<Commondity> list);
}
