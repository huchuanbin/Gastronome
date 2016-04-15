package com.hcb.gastronome.ui.fragment.delicious;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.delicious.DishesData;
import com.hcb.gastronome.mvp.model.delicious.TabData;
import com.hcb.gastronome.mvp.presenter.DishesPresenter;
import com.hcb.gastronome.mvp.view_controller.DeliciousView;
import com.hcb.gastronome.mvp.view_controller.DishesView;
import com.hcb.gastronome.ui.activity.MenuActivity;
import com.hcb.gastronome.ui.adapter.DishesAdapter;
import com.hcb.gastronome.ui.base.BaseRecycleFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by huchuanbin on 16/4/1.
 */
public class DishesFragment extends BaseRecycleFragment implements DishesView {
    private List<DishesData.ResultBean.DataBean> list;
    private int cId;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Inject
    DishesPresenter dishesPresenter;
    @Inject
    DishesAdapter dishesAdapter;

    public static DishesFragment getInstance() {
        return new DishesFragment();
    }

    public void getCId(int cId) {
        this.cId = cId;
    }

    @Override
    public void loadData(int cid) {
        dishesPresenter.getTabData(cid);

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initInjector(this).inject(this);
        dishesPresenter.setControllerView(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(linearLayoutManager);
        rvList.setAdapter(dishesAdapter);
        loadData(cId);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_dishs;
    }


    @Override
    public void loadServerData(boolean loadSuccess, DishesData dishesData) {
        if (loadSuccess) {
            list = new ArrayList<>();
            list.addAll(dishesData.getResult().getData());
            dishesAdapter.autoInsertItems(list);
            dishesAdapter.setOnItemClickListener((adapterView, view, i, l) -> interfaceJump(Integer.parseInt(list.get(i).getId()),list.get(i).getTitle(),list.get(i).getAlbums().get(0)));
        }
        else {
        }

    }

    private void interfaceJump(int id,String title,String albums) {
        Intent intent=new Intent(getContext(), MenuActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("title",title);
        intent.putExtra("albums",albums);
        getActivity().startActivity(intent);
    }
}
