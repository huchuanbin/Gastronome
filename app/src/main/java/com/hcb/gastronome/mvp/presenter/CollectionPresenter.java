package com.hcb.gastronome.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.bmob._User;
import com.hcb.gastronome.mvp.view_controller.CollectionView;
import com.hcb.gastronome.ui.activity.MainActivity;

import javax.inject.Inject;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by huchuanbin on 16/4/15.
 */
public class CollectionPresenter extends BasePresenter<CollectionView>{
    private Context context;
    @Inject
    public CollectionPresenter(@ContextLevel(ContextLevel.FRAGMENT)Context context){
        this.context=context;
    }
    public void getData(){
        BmobQuery query=new BmobQuery("_User");
        query.getObject(context,MainActivity.userId, new GetListener<_User>() {
            @Override
            public void onFailure(int i, String s) {

            }

            @Override
            public void onSuccess(_User user) {
                getControllerView().loadServerData(user);
            }

        });
//        BmobQuery<_User>query=new BmobQuery<>();
//        query.getObject(context, MainActivity.userId, new GetCallback() {
//            @Override
//            public void onSuccess(JSONObject jsonObject) {
//                Log.d("CollectionPresenter", "jsonObject:" + jsonObject);
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//                Log.d("CollectionPresenter", i + s);
//            }
//        });
    }
}
