package com.hcb.gastronome.mvp.view_controller;

/**
 * Created by huchuanbin on 16/4/2.
 */
public interface UserView extends BaseView {
    void result(int type, boolean success, String msg);
}
