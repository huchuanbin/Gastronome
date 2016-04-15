package com.hcb.gastronome.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.home.AllHomeData;
import com.hcb.gastronome.mvp.model.home.HomeData;
import com.hcb.gastronome.mvp.presenter.HomePresenter;
import com.hcb.gastronome.mvp.view_controller.HomeView;
import com.hcb.gastronome.ui.activity.MenuActivity;
import com.hcb.gastronome.ui.adapter.HomeAdapter;
import com.hcb.gastronome.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class HomeFragment extends BaseFragment implements HomeView {
    @Bind(R.id.rlBar)
    RelativeLayout rlBar;
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
        adaptStatusBar(rlBar);
        homePresenter.getBannerData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        homeAdapter.setHeaderCount(1);
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }


    @Override
    public void result(boolean success, AllHomeData allHomeData) {
        homeAdapter.setBannerData(allHomeData.getListBanner());
        homeAdapter.resetItems(allHomeData.getListHome());
        homeAdapter.setOnItemClickListener((adapterView, view, i, l) -> interfaceJump(Integer.parseInt(allHomeData.getListHome().get(i-1).getId()), allHomeData.getListHome().get(i-1).getTitle(), allHomeData.getListHome().get(i-1).getAlbums()));
    }

    private void interfaceJump(int id, String title, String albums) {
        Intent intent = new Intent(getContext(), MenuActivity.class);
        Log.d("DishesFragment", "i:" + id);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("albums", albums);
        getActivity().startActivity(intent);
    }
}
