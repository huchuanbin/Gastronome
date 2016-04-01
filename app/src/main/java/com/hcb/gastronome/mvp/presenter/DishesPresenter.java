package com.hcb.gastronome.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.delicious.DishesData;
import com.hcb.gastronome.mvp.view_controller.DishesView;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
import com.trello.rxlifecycle.FragmentLifecycleProvider;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by huchuanbin on 16/4/1.
 */
public class DishesPresenter extends BasePresenter<DishesView> {

    private Context context;
    private FragmentLifecycleProvider fragmentLifecycleProvider;
    private List<DishesData.ResultBean.DataBean> listdDishesData;

    @Inject
    public DishesPresenter(@ContextLevel(ContextLevel.FRAGMENT) Context context, FragmentLifecycleProvider fragmentLifecycleProvider) {
        this.context = context;
        this.fragmentLifecycleProvider = fragmentLifecycleProvider;
    }

    public void getTabData(int cid) {
        int id = 46;
        String url = "http://apis.juhe.cn/cook/index";
        Parameters parameters = new Parameters();
        parameters.add("cid", cid);

        JuheData.executeWithAPI(context, id, url, JuheData.GET, parameters, new DataCallBack() {
            @Override
            public void onSuccess(int i, String s) {
                DishesData dishesData = JSON.parseObject(s, DishesData.class);
                Log.d("DishesPresenter", s);
                getControllerView().loadServerData(true, dishesData);

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
