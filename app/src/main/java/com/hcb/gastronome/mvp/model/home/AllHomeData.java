package com.hcb.gastronome.mvp.model.home;

import java.util.List;

/**
 * Created by huchuanbin on 16/4/7.
 */
public class AllHomeData {
    private List<BannerData> listBanner;
    private List<HomeData> listHome;

    public List<BannerData> getListBanner() {
        return listBanner;
    }

    public void setListBanner(List<BannerData> listBanner) {
        this.listBanner = listBanner;
    }

    public List<HomeData> getListHome() {
        return listHome;
    }

    public void setListHome(List<HomeData> listHome) {
        this.listHome = listHome;
    }
}
