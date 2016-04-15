package com.hcb.gastronome.ui.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.bmob._User;
import com.hcb.gastronome.mvp.presenter.CollectionPresenter;
import com.hcb.gastronome.mvp.view_controller.CollectionView;
import com.hcb.gastronome.ui.activity.MenuActivity;
import com.hcb.gastronome.ui.adapter.CollectionAdapter;
import com.hcb.gastronome.ui.base.BaseRecycleFragment;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by huchuanbin on 16/4/15.
 */
public class CollectionFragment extends BaseRecycleFragment implements CollectionView {
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Inject
    CollectionPresenter collectionPresenter;
    @Inject
    CollectionAdapter collectionAdapter;

    public static CollectionFragment getInstance() {
        return new CollectionFragment();
    }

    @Override
    public void loadData(int cid) {
        collectionPresenter.getData();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_collection;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initInjector(this).inject(this);
        collectionPresenter.setControllerView(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(linearLayoutManager);
        rvList.setAdapter(collectionAdapter);
        loadData(1);
    }

    @Override
    public void loadServerData(_User user) {
        collectionAdapter.autoInsertItems(user.getInterest());
        collectionAdapter.setOnItemClickListener((adapterView, view, i, l) -> interfaceJump(Integer.parseInt(user.getInterest().get(i).getId()), user.getInterest().get(i).getTitle(), user.getInterest().get(i).getAlbums()));
    }

    private void interfaceJump(int id, String title, String albums) {
        Intent intent = new Intent(getContext(), MenuActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("albums", albums);
        getActivity().startActivity(intent);
    }

}
