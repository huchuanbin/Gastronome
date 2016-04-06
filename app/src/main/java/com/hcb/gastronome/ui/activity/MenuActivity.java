package com.hcb.gastronome.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.delicious.MenuData;
import com.hcb.gastronome.mvp.presenter.MenuPresenter;
import com.hcb.gastronome.mvp.view_controller.MenuView;
import com.hcb.gastronome.ui.adapter.MenuAdapter;
import com.hcb.gastronome.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by huchuanbin on 16/4/2.
 */
public class MenuActivity extends BaseActivity implements MenuView {
    private Intent intent;
    private int id;
    private String title;
    private String albums;
    @Bind(R.id.rlBar)
    RelativeLayout rlBar;
    @Bind(R.id.tool_bar)
    Toolbar toolbar;
    @Bind(R.id.recycleView)
    RecyclerView recyclerView;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Inject
    MenuPresenter menuPresenter;
    @Inject
    MenuAdapter menuAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_menu;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initInjector(this).inject(this);
        menuPresenter.setControllerView(this);
        title = "   ";
        intent = getIntent();
        id = intent.getIntExtra("id", 0);
        title += intent.getStringExtra("title");
        albums = intent.getStringExtra("albums");
        toolbar.setTitle(title);
        Glide.with(this).load(albums)
                .placeholder(R.mipmap.ic_launcher)
                .dontAnimate()
                .into(imageView);
        setSupportActionBar(toolbar);
        adaptStatusBar(rlBar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(menuAdapter);
        menuPresenter.getMenuData(id);
    }
@OnClick(R.id.rlBar)
public void backOff(){
    finish();
}
    @Override
    public void result(boolean success, MenuData menuData) {
        if (success) {
            Log.d("MenuActivity", menuData.getReason());
            menuAdapter.setHeaderCount(1);
            menuAdapter.setTitle(menuData);
            recyclerView.scrollBy(0, 1);
            menuAdapter.autoInsertItems(menuData.getResult().getData().get(0).getSteps());
        }
    }
}
