package com.hcb.gastronome.mvp.view_controller;

import com.hcb.gastronome.mvp.model.delicious.DishesData;

/**
 * Created by huchuanbin on 16/4/1.
 */
public interface DishesView extends BaseView {
    void loadServerData(boolean loadSuccess, DishesData dishesData);
}
