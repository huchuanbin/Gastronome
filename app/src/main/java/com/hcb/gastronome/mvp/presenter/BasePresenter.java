package com.hcb.gastronome.mvp.presenter;

import com.hcb.gastronome.mvp.view_controller.BaseView;

import dagger.Component;

/**
 * Created by huchuanbin on 16/3/31.
 */
public abstract class BasePresenter<T extends BaseView> {
    private T controllerView;
    public  void setControllerView(T view){
        controllerView=view;
    }
    public T getControllerView(){
        return controllerView;
    }
}
