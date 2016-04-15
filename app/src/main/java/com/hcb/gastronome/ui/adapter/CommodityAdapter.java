package com.hcb.gastronome.ui.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.hcb.gastronome.R;
import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.bmob.Commondity;
import com.hcb.gastronome.ui.adapter.base.BaseRecycleAdapter;
import com.hcb.gastronome.ui.adapter.base.ViewHolder;

import javax.inject.Inject;

/**
 * Created by huchuanbin on 16/4/15.
 */
public class CommodityAdapter extends BaseRecycleAdapter<Commondity> {
    @Inject
    public CommodityAdapter(@ContextLevel(ContextLevel.FRAGMENT) Context context) {
        super(context);
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_dishs;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Commondity data, int typePosition, int position) {
        Glide.with(context)
                .load(data.getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .dontAnimate()
                .into(holder.getImageView(R.id.iv_albums));
        holder.getTextView(R.id.tv_title).setText(data.getTitle().toString());
    }
}
