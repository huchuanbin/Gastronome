package com.hcb.gastronome.ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class TabViewPager extends ViewPager {
    private boolean pagingEnable = true;

    public TabViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (pagingEnable) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (pagingEnable) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    public void setPagingEnable(boolean pagingEnable) {
        this.pagingEnable = pagingEnable;
    }
}
