package com.hcb.gastronome.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.bmob.Commondity;
import com.hcb.gastronome.mvp.view_controller.CommodityView;

import java.util.List;

import javax.inject.Inject;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by huchuanbin on 16/4/15.
 */
public class CommodityPresenter extends BasePresenter<CommodityView> {
    private Context context;

    @Inject
    public CommodityPresenter(@ContextLevel(ContextLevel.FRAGMENT) Context context) {
        this.context = context;
    }
    public void getCommodity(){
        BmobQuery<Commondity>query=new BmobQuery<>();
        query.findObjects(context, new FindListener<Commondity>() {
            @Override
            public void onSuccess(List<Commondity> list) {
                getControllerView().loadServerData(list);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
