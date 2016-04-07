package com.hcb.gastronome.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.hcb.gastronome.R;
import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.home.BannerData;
import com.hcb.gastronome.mvp.model.home.HomeData;
import com.hcb.gastronome.mvp.view_controller.HomeView;
import com.hcb.gastronome.ui.adapter.base.BaseRecycleAdapter;
import com.hcb.gastronome.ui.adapter.base.ViewHolder;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by huchuanbin on 16/4/6.
 */
public class HomeAdapter extends BaseRecycleAdapter<HomeData> {
    private List<BannerData> bannerData;
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

    public void setBannerData(List<BannerData> bannerDatas) {
        this.bannerData = bannerDatas;
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
        if (bannerData == null) {
            return 0;
        }
        return super.getItemCount();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, HomeData data, int typePosition, int position) {
        if (getItemViewType(typePosition) == TYPE_BANNER && bannerData != null) {
            RollPagerView pagerView = holder.get(R.id.banner_pager);
            pagerView.setHintView(new ColorPointHintView(context, Color.WHITE, 0x80FFFFFF));
            pagerView.setAdapter(new BannerPagerAdapter(pagerView, bannerData));
            pagerView.setAnimationDurtion(1700);
        }
        if (getItemViewType(typePosition) == TYPE_TOPIC) {
            Glide.with(context)
                    .load(getItems().get(position).getAlbums())
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .dontAnimate()
                    .into(holder.getImageView(R.id.iv_albums));
        }
    }

    private class BannerPagerAdapter extends LoopPagerAdapter {
        private List<BannerData> bannerData;

        public BannerPagerAdapter(RollPagerView viewPager, List<BannerData> bannerData) {
            super(viewPager);
            this.bannerData = bannerData;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setOnClickListener(view -> Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show());
            Glide.with(imageView.getContext())
                    .load(bannerData.get(position).getUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .dontAnimate()
                    .into(imageView);
            return imageView;
        }

        @Override
        protected int getRealCount() {
            return bannerData.size();
        }
    }
}
