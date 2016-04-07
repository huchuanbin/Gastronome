package com.hcb.gastronome.ui.adapter;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.hcb.gastronome.R;
import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.delicious.MenuData;
import com.hcb.gastronome.ui.adapter.base.BaseRecycleAdapter;
import com.hcb.gastronome.ui.adapter.base.ViewHolder;

import javax.inject.Inject;

/**
 * Created by huchuanbin on 16/4/2.
 */
public class MenuAdapter extends BaseRecycleAdapter<MenuData.ResultBean.DataBean.StepsBean> {
    public static final int TYPE_TITLE = 1;
    public static final int TYPE_STEPS = 2;
    private MenuData menuData;
    private Context context;

    @Inject
    public MenuAdapter(@ContextLevel(ContextLevel.ACTIVITY) Context context) {
        super(context);
        this.context = context;
    }
    public void setTitle(MenuData menuData){
        this.menuData=menuData;
        Log.d("MenuAdapter", menuData.getResult().getData().get(0).getTags());

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        } else {
            return TYPE_STEPS;
        }
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        if (TYPE_TITLE == viewType) {
            return R.layout.item_menu_title;
        } else {
            return R.layout.item_menu_steps;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, MenuData.ResultBean.DataBean.StepsBean data, int typePosition, int position) {
        if (getItemViewType(typePosition) == TYPE_TITLE) {
            holder.getTextView(R.id.tvImtro).setText(menuData.getResult().getData().get(0).getImtro());
            holder.getTextView(R.id.tvIngredients).setText(menuData.getResult().getData().get(0).getIngredients());
            holder.getTextView(R.id.tvBurden).setText(menuData.getResult().getData().get(0).getBurden());
        }
        if (getItemViewType(typePosition) == TYPE_STEPS) {
            Glide.with(context)
                    .load(getItems().get(position).getImg())
                    .placeholder(R.mipmap.ic_launcher)
                    .dontAnimate()
                    .into(holder.getImageView(R.id.ivImg));
            holder.getTextView(R.id.tvStep).setText(getItems().get(position).getStep());
        }
    }

    @Override
    public int getItemCount() {
     if (menuData==null){
         return 0;
     }
        return super.getItemCount();
    }

}
