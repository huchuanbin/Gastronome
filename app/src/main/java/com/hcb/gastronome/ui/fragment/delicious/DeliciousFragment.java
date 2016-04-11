package com.hcb.gastronome.ui.fragment.delicious;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import butterknife.OnClick;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class DeliciousFragment extends BaseFragment implements DeliciousView {
    private List<BaseFragment> listFragment;
    private List<String> listTitle;
    private List<TabData.ResultBean.ListBean> listBeen;
    private PageAdapter adapter;
    private int num;
    private boolean isOpen = false;
    private DishesFragment dishesFragment;
    @Bind(R.id.bar_layout)
    AppBarLayout barLayout;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind({R.id.iv1_1, R.id.iv1_2, R.id.iv1_3, R.id.iv1_4, R.id.iv1_5, R.id.iv1_6, R.id.iv1_7
            , R.id.iv2_1, R.id.iv2_2, R.id.iv2_3, R.id.iv2_4, R.id.iv2_5, R.id.iv2_6, R.id.iv2_7
            , R.id.iv3_1, R.id.iv3_2, R.id.iv3_3, R.id.iv3_4, R.id.iv3_5, R.id.iv3_6, R.id.iv3_7
            , R.id.iv4_1, R.id.iv4_2, R.id.iv4_3, R.id.iv4_4, R.id.iv4_5, R.id.iv4_6, R.id.iv4_7})
    List<FloatingActionButton> fabList;
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
        fabState(isOpen);
    }

    private void loadData(int b) {
        deliciousPresenter.getTabData(b);
    }

    @OnClick(R.id.fab)
    public void fab() {
        if (isOpen) {
            isOpen = false;
            fabState(isOpen);
        } else {
            for (int i=0;i<fabList.size();i++) {
                openFab(i);
            }
            isOpen = true;
            fabState(isOpen);
        }
    }

    private void fabState(boolean isOpen) {
        if (isOpen) {
            for (FloatingActionButton fab : fabList) {
                fab.setVisibility(View.VISIBLE);
            }
        } else {
            for (FloatingActionButton fab : fabList) {
                fab.setVisibility(View.GONE);
            }
        }
    }

    private void openFab(int position) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(300);
        valueAnimator.setObjectValues(new PointF(fab.getLeft(), fab.getTop()));
        valueAnimator.setInterpolator(new LinearInterpolator());
        if (position<4) {
            final float x = (float) Math.sin(Math.PI / 2 * (position+1) / 4);
            final float y = (float) Math.cos(Math.PI / 2 * (position+1) / 4);
            valueAnimator.setEvaluator((fraction, start, end) -> {
                PointF pointF = new PointF();
                pointF.x = fab.getLeft() - fraction * 3 * 100 * x;
                pointF.y = fab.getTop() - fraction * 3 * 100 * y;
                return pointF;
            });
            valueAnimator.start();
            valueAnimator.addUpdateListener(animator -> {
                PointF pointF = (PointF) animator.getAnimatedValue();
                fabList.get(position).setX(pointF.x);
                fabList.get(position).setY(pointF.y);
            });
        }
        else if (position<12){
            final float x = (float) Math.sin(Math.PI / 2 * (position-3) / 8);
            final float y = (float) Math.cos(Math.PI / 2 * (position-3) / 8);
            valueAnimator.setEvaluator((fraction, start, end) -> {
                PointF pointF = new PointF();
                pointF.x = fab.getLeft() - fraction * 3 * 200 * x;
                pointF.y = fab.getTop() - fraction * 3 * 200 * y;
                return pointF;
            });
            valueAnimator.start();
            valueAnimator.addUpdateListener(animator -> {
                PointF pointF = (PointF) animator.getAnimatedValue();
                fabList.get(position).setX(pointF.x);
                fabList.get(position).setY(pointF.y);
            });
        }
        else {
            final float x = (float) Math.sin(Math.PI / 2 * (position-11) / 16);
            final float y = (float) Math.cos(Math.PI / 2 * (position-11) / 16);
            valueAnimator.setEvaluator((fraction, start, end) -> {
                PointF pointF = new PointF();
                pointF.x = fab.getLeft() - fraction * 3 * 300 * x;
                pointF.y = fab.getTop() - fraction * 3 * 300 * y;
                return pointF;
            });
            valueAnimator.start();
            valueAnimator.addUpdateListener(animator -> {
                PointF pointF = (PointF) animator.getAnimatedValue();
                fabList.get(position).setX(pointF.x);
                fabList.get(position).setY(pointF.y);
            });
        }
//        valueAnimator.setEvaluator((fraction, start, end) -> {
//            PointF pointF = new PointF();
//            pointF.x = fab.getLeft() - fraction * 5 * 100;
//            pointF.y = fab.getTop() - fraction * 5 * 100;
//            return pointF;
//        });
//        valueAnimator.start();
//        valueAnimator.addUpdateListener(animator -> {
//            PointF pointF = (PointF) animator.getAnimatedValue();
//            fabList.get(0).setX(pointF.x);
//            fabList.get(0).setY(pointF.y);
//            Log.d("DeliciousFragment", "Math.sin(1):" + Math.sin(Math.PI / 6));
//        });
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
    }
}
