package com.hcb.gastronome.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by huchuanbin on 16/4/2.
 */
public class MenuActivity extends BaseActivity {
    private Intent intent;
    private int id;
    @Bind(R.id.tool_bar)
    Toolbar toolbar;

    @Override
    public int getLayout() {
        return R.layout.activity_menu;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setSupportActionBar(toolbar);

        intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }
}
