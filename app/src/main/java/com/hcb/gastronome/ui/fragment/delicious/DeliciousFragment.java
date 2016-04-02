package com.hcb.gastronome.ui.fragment.delicious;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.delicious.TabData;
import com.hcb.gastronome.mvp.presenter.DeliciousPresenter;
import com.hcb.gastronome.mvp.view_controller.DeliciousView;
import com.hcb.gastronome.ui.adapter.PageAdapter;
import com.hcb.gastronome.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class DeliciousFragment extends BaseFragment implements DeliciousView {
    private List<BaseFragment> listFragment;
    private List<String> listTitle;
    private List<TabData.ResultBean.ListBean> listBeen;
    private PageAdapter adapter;
    private int num;
    private DishesFragment dishesFragment;
    @Bind(R.id.bar_layout)
    AppBarLayout barLayout;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.vp)
    ViewPager vp;
    @Inject
    DeliciousPresenter deliciousPresenter;


    public static DeliciousFragment getInstance() {
        return new DeliciousFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_delicious;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initInjector(this).inject(this);
        deliciousPresenter.setControllerView(this);
        adaptStatusBar(barLayout);
        listFragment = new ArrayList<>();
        listTitle = new ArrayList<>();
        loadData(10001);
    }

    private void loadData(int b) {
        deliciousPresenter.getTabData(b);
    }


    @Override
    public void loadServerData(boolean loadSuccess, TabData tabData) {
        if (loadSuccess) {
            listBeen = tabData.getResult().get(0).getList();
            tvTitle.setText(tabData.getResult().get(0).getName());
            num = listBeen.size();
            for (int i = 0; i < num; i++) {
                dishesFragment = DishesFragment.getInstance();
                listFragment.add(dishesFragment);
                dishesFragment.getCId(Integer.parseInt(listBeen.get(i).getId()));
                listTitle.add(listBeen.get(i).getName());
            }
            adapter = new PageAdapter(getChildFragmentManager(), listFragment, listTitle);
            vp.setAdapter(adapter);
            vp.setOffscreenPageLimit(num);
            tabLayout.setupWithViewPager(vp);
        }
        Log.d("DeliciousFragment", tabData.getReason()) ;
    }
}
