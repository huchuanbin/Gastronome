package com.hcb.gastronome.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.bmob.Banner;
import com.hcb.gastronome.mvp.model.bmob.Home;
import com.hcb.gastronome.mvp.model.home.AllHomeData;
import com.hcb.gastronome.mvp.model.home.BannerData;
import com.hcb.gastronome.mvp.model.home.HomeData;
import com.hcb.gastronome.mvp.view_controller.HomeView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by huchuanbin on 16/4/5.
 */
public class HomePresenter extends BasePresenter<HomeView> {
    private boolean loadSuccess;
    private Context context;
    private BmobQuery query;
    private BannerData bannerData;
    private HomeData homeData;
    private List<BannerData> listBanner;
    private List<HomeData> listHome;
    private AllHomeData allHomeData;

    @Inject
    public HomePresenter(@ContextLevel(ContextLevel.FRAGMENT) Context context) {
        this.context = context;
    }

    public void getBannerData() {
        Log.d("HomePresenter", "Corbin");
        listBanner = new ArrayList<>();
        listHome = new ArrayList<>();
        allHomeData = new AllHomeData();
        query = new BmobQuery("Banner");
        query.findObjects(context, new FindListener<Banner>() {
            @Override
            public void onSuccess(List<Banner> list) {
                for (int i = 0; i < list.size(); i++) {
                    bannerData = new BannerData();
                    bannerData.setId(list.get(i).getId());
                    bannerData.setUrl(list.get(i).getPic().getFileUrl(context));
                    bannerData.setType(list.get(i).getType());
                    listBanner.add(bannerData);
                }
                allHomeData.setListBanner(listBanner);
                query = new BmobQuery("Home");
                query.findObjects(context, new FindListener<Home>() {
                    @Override
                    public void onSuccess(List<Home> list) {
                        for (int i = 0; i < list.size(); i++) {
                            homeData = new HomeData();
                            homeData.setId(list.get(i).getId());
                            homeData.setTitle(list.get(i).getTitle());
                            homeData.setAlbums(list.get(i).getAlbums());
                            listHome.add(homeData);
                        }

                        allHomeData.setListHome(listHome);
                        getControllerView().result(true,allHomeData);
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });

            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
