package com.hcb.gastronome.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.delicious.TabData;
import com.hcb.gastronome.mvp.view_controller.DeliciousView;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
import com.trello.rxlifecycle.FragmentLifecycleProvider;

import javax.inject.Inject;

/**
 * Created by huchuanbin on 16/3/31.
 */
public class DeliciousPresenter extends BasePresenter<DeliciousView> {

    private Context context;
    private FragmentLifecycleProvider fragmentLifecycleProvider;
    private boolean loadSuccess;

    @Inject
    public DeliciousPresenter(@ContextLevel(ContextLevel.FRAGMENT) Context context, FragmentLifecycleProvider fragmentLifecycleProvider) {
        this.context = context;
        this.fragmentLifecycleProvider = fragmentLifecycleProvider;
    }

    public void getTabData(int parentid) {
        int id = 46;
        String url = "http://apis.juhe.cn/cook/category";
        Parameters parameters = new Parameters();
        parameters.add("parentid", parentid);

        JuheData.executeWithAPI(context, id, url, JuheData.GET, parameters, new DataCallBack() {
            @Override
            public void onSuccess(int i, String s) {
                Log.d("DeliciousPresenter", s);
                TabData tabData = JSON.parseObject(s, TabData.class);
                if (tabData.getResultcode().equals("112")) {
                    loadSuccess = false;
                } else {
                    loadSuccess=true;
                }
                getControllerView().loadServerData(loadSuccess, tabData);

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
