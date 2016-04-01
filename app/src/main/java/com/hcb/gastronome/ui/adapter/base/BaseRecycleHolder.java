package com.hcb.gastronome.ui.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by huchuanbin on 16/4/1.
 */
public class BaseRecycleHolder extends RecyclerView.ViewHolder {
    private ViewHolder viewHolder;

    public BaseRecycleHolder(View itemView) {
        super(itemView);
        viewHolder = ViewHolder.getViewHolder(itemView);
    }

    public ViewHolder getViewHolder() {
        return viewHolder;
    }
}
