package com.hcb.gastronome.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.delicious.DishesData;
import com.hcb.gastronome.mvp.model.delicious.MenuData;
import com.hcb.gastronome.mvp.view_controller.MenuView;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

import javax.inject.Inject;

/**
 * Created by huchuanbin on 16/4/2.
 */
public class MenuPresenter extends BasePresenter<MenuView>{
    private boolean loadSuccess;
    private Context context;
    @Inject
    public MenuPresenter (@ContextLevel(ContextLevel.ACTIVITY)Context context){
        this.context=context;
    }
    public void getMenuData(int i){
        int id = 46;
        String url = "http://apis.juhe.cn/cook/queryid";
        Parameters parameters = new Parameters();
        parameters.add("id", i);

        JuheData.executeWithAPI(context, id, url, JuheData.GET, parameters, new DataCallBack() {
            @Override
            public void onSuccess(int i, String s) {
                MenuData menuData = JSON.parseObject(s, MenuData.class);
                Log.d("DishesPresenter", s);
                if (menuData.getResultcode().equals("200")){
                    loadSuccess=true;
                }
                else {
                    loadSuccess=false;
                }
                getControllerView().result(loadSuccess, menuData);

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {
                Log.d("JuheApi", s);
            }
        });
    }
}
