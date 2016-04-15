package com.hcb.gastronome.ui.base;

import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

/**
 * Author:    valuesfeng
 * Version    V1.0
 * Date:      16/1/7
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/7          valuesfeng              1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseEventActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
