package com.hcb.gastronome.mvp.view_controller;

import com.hcb.gastronome.mvp.model.delicious.MenuData;

/**
 * Created by huchuanbin on 16/4/2.
 */
public interface MenuView extends BaseView{
    void result( boolean success, MenuData menuData);
}
