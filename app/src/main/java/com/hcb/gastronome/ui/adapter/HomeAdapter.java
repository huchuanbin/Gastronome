package com.hcb.gastronome.ui.adapter;

import android.content.Context;

import com.hcb.gastronome.R;
import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.home.BannerData;
import com.hcb.gastronome.mvp.model.home.HomeData;
import com.hcb.gastronome.mvp.view_controller.HomeView;
import com.hcb.gastronome.ui.adapter.base.BaseRecycleAdapter;
import com.hcb.gastronome.ui.adapter.base.ViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by huchuanbin on 16/4/6.
 */
public class HomeAdapter extends BaseRecycleAdapter<HomeData> {
    private List<BannerData> bannerDatas;
    private Context context;
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_TOPIC = 2;

    @Inject
    public HomeAdapter(@ContextLevel(ContextLevel.FRAGMENT) Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else {
            return TYPE_TOPIC;
        }
    }

    public void setBannerDatas(List<BannerData> bannerDatas) {
        this.bannerDatas = bannerDatas;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        if (TYPE_BANNER == viewType) {
            return R.layout.home_banner;
        } else {
            return R.layout.home_topic;
        }
    }

    @Override
    public int getItemCount() {
        if (bannerDatas == null) {
            return 0;
        }
        return super.getItemCount();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, HomeData data, int typePosition, int position) {
        if (getItemViewType(typePosition) == TYPE_BANNER && bannerDatas != null) {

        }
        if (getItemViewType(typePosition) == TYPE_TOPIC) {

        }
    }
}
