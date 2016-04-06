package com.hcb.gastronome.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.home.HomeData;
import com.hcb.gastronome.mvp.presenter.HomePresenter;
import com.hcb.gastronome.mvp.view_controller.HomeView;
import com.hcb.gastronome.ui.adapter.HomeAdapter;
import com.hcb.gastronome.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class HomeFragment extends BaseFragment implements HomeView{
    @Bind(R.id.barLayout)
    View view;
    @Bind(R.id.rvList)
    RecyclerView recyclerView;
    @Inject
    HomeAdapter homeAdapter;
    @Inject
    HomePresenter homePresenter;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initInjector(this).inject(this);
        homePresenter.setControllerView(this);
        adaptStatusBar(view);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }
    @OnClick(R.id.ivSearch)
    public void search(){
        Toast.makeText(getContext(), "Corbin", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void result(boolean success, HomeData homeData) {

    }
}
