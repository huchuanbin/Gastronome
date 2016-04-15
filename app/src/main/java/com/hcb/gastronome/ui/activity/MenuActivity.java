package com.hcb.gastronome.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.bmob._User;
import com.hcb.gastronome.mvp.model.delicious.MenuData;
import com.hcb.gastronome.mvp.model.home.HomeData;
import com.hcb.gastronome.mvp.presenter.MenuPresenter;
import com.hcb.gastronome.mvp.view_controller.MenuView;
import com.hcb.gastronome.ui.adapter.MenuAdapter;
import com.hcb.gastronome.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

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
    @Bind(R.id.fab)
    FloatingActionButton fab;
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
        intent = getIntent();
        id = intent.getIntExtra("id", 0);
        title = intent.getStringExtra("title");
        albums = intent.getStringExtra("albums");
        setSupportActionBar(toolbar);
        adaptStatusBar(rlBar);
        toolbar.setTitle(title);
        Glide.with(this).load(albums)
                .placeholder(R.mipmap.ic_launcher)
                .dontAnimate()
                .into(imageView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(menuAdapter);
        menuPresenter.getMenuData(id);
    }

    @OnClick(R.id.fab)
    public void interest() {
        _User user = new _User();
        HomeData homeData=new HomeData();
        homeData.setId(id+"");
        homeData.setAlbums(albums);
        homeData.setTitle(title);
        user.addUnique("interest",homeData);
        user.update(this,MainActivity.userId, new UpdateListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MenuActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("MenuActivity", i + s);
            }
        });
    }

    @Override
    public void result(boolean success, MenuData menuData) {
        if (success) {
            menuAdapter.setHeaderCount(1);
            menuAdapter.setTitle(menuData);
            recyclerView.scrollBy(0, 1);
            menuAdapter.autoInsertItems(menuData.getResult().getData().get(0).getSteps());
        }
    }
}
