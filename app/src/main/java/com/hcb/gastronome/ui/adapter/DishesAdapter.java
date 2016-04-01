package com.hcb.gastronome.ui.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.hcb.gastronome.R;
import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.delicious.DishesData;
import com.hcb.gastronome.mvp.presenter.DishesPresenter;
import com.hcb.gastronome.ui.adapter.base.BaseRecycleAdapter;
import com.hcb.gastronome.ui.adapter.base.ViewHolder;

import javax.inject.Inject;

/**
 * Created by huchuanbin on 16/4/1.
 */
public class DishesAdapter extends BaseRecycleAdapter<DishesData.ResultBean.DataBean> {

    private Context context;
    private DishesPresenter dishesPresenter;

    @Inject
    public DishesAdapter(@ContextLevel(ContextLevel.FRAGMENT) Context context, DishesPresenter dishesPresenter) {
        super(context);
        this.context = context;
        this.dishesPresenter = dishesPresenter;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_dishs;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, DishesData.ResultBean.DataBean data, int position) {
        Glide.with(context)
                .load(data.getAlbums().get(0))
                .placeholder(R.mipmap.ic_launcher)
                .dontAnimate()
                .into(holder.getImageView(R.id.iv_albums));
        holder.getTextView(R.id.tv_title).setText(data.getTitle().toString());
    }


}
