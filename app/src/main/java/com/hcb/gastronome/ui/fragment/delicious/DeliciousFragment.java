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
    private int[] fabs = {R.id.fab1_1, R.id.fab1_2, R.id.fab1_3, R.id.fab1_4, R.id.fab1_5, R.id.fab1_6, R.id.fab1_7
            , R.id.fab2_1, R.id.fab2_2, R.id.fab2_3, R.id.fab2_4, R.id.fab2_5, R.id.fab2_6, R.id.fab2_7
            , R.id.fab3_1, R.id.fab3_2, R.id.fab3_3, R.id.fab3_4, R.id.fab3_5, R.id.fab3_6, R.id.fab3_7
            , R.id.fab4_1, R.id.fab4_2, R.id.fab4_3, R.id.fab4_4, R.id.fab4_5, R.id.fab4_6, R.id.fab4_7};
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
    @Bind({R.id.fab1_1, R.id.fab1_2, R.id.fab1_3, R.id.fab1_4, R.id.fab1_5, R.id.fab1_6, R.id.fab1_7
            , R.id.fab2_1, R.id.fab2_2, R.id.fab2_3, R.id.fab2_4, R.id.fab2_5, R.id.fab2_6, R.id.fab2_7
            , R.id.fab3_1, R.id.fab3_2, R.id.fab3_3, R.id.fab3_4, R.id.fab3_5, R.id.fab3_6, R.id.fab3_7
            , R.id.fab4_1, R.id.fab4_2, R.id.fab4_3, R.id.fab4_4, R.id.fab4_5, R.id.fab4_6, R.id.fab4_7})
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
            for (int i = 0; i < fabList.size(); i++) {
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
        if (position < 4) {
            final float x = (float) Math.sin(Math.PI * (position) / 3);
            final float y = (float) Math.cos(Math.PI * (position) / 3);
            valueAnimator.setEvaluator((fraction, start, end) -> {
                PointF pointF = new PointF();
                pointF.x = fab.getLeft() - fraction * 3 * 50 * x;
                pointF.y = fab.getTop() - fraction * 3 * 50 * y;
                return pointF;
            });
            valueAnimator.start();
            valueAnimator.addUpdateListener(animator -> {
                PointF pointF = (PointF) animator.getAnimatedValue();
                fabList.get(position).setX(pointF.x);
                fabList.get(position).setY(pointF.y);
            });
        } else if (position < 12) {
            final float x = (float) Math.sin(Math.PI * (position - 4) / 7);
            final float y = (float) Math.cos(Math.PI * (position - 4) / 7);
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
        } else {
            final float x = (float) Math.sin(Math.PI * (position - 12) / 15);
            final float y = (float) Math.cos(Math.PI * (position - 12) / 15);
            valueAnimator.setEvaluator((fraction, start, end) -> {
                PointF pointF = new PointF();
                pointF.x = fab.getLeft() - fraction * 3 * 150 * x;
                pointF.y = fab.getTop() - fraction * 3 * 150 * y;
                return pointF;
            });
            valueAnimator.start();
            valueAnimator.addUpdateListener(animator -> {
                PointF pointF = (PointF) animator.getAnimatedValue();
                fabList.get(position).setX(pointF.x);
                fabList.get(position).setY(pointF.y);
            });
        }
    }

    @Override
    public void loadServerData(boolean loadSuccess, TabData tabData) {
        if (loadSuccess) {
            listFragment = new ArrayList<>();
            listTitle = new ArrayList<>();
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
            Log.d("DeliciousFragment", tabData.getResult().get(0).getName());
        }
    }

    @OnClick({R.id.fab1_1, R.id.fab1_2, R.id.fab1_3, R.id.fab1_4, R.id.fab1_5, R.id.fab1_6, R.id.fab1_7
            , R.id.fab2_1, R.id.fab2_2, R.id.fab2_3, R.id.fab2_4, R.id.fab2_5, R.id.fab2_6, R.id.fab2_7
            , R.id.fab3_1, R.id.fab3_2, R.id.fab3_3, R.id.fab3_4, R.id.fab3_5, R.id.fab3_6, R.id.fab3_7
            , R.id.fab4_1, R.id.fab4_2, R.id.fab4_3, R.id.fab4_4, R.id.fab4_5, R.id.fab4_6, R.id.fab4_7})
    public void fabClick(View view) {

        switch (view.getId()) {

            case R.id.fab1_1:
                jump(10028);
                break;
            case R.id.fab1_2:
                jump(10027);
                break;
            case R.id.fab1_3:
                jump(10026);
                break;
            case R.id.fab1_4:
                jump(10025);
                break;
            case R.id.fab1_5:
                jump(10024);
                break;
            case R.id.fab1_6:
                jump(10023);
                break;
            case R.id.fab1_7:
                jump(10022);
                break;
            case R.id.fab2_1:
                jump(10021);
                break;
            case R.id.fab2_2:
                jump(10020);
                break;
            case R.id.fab2_3:
                jump(10019);
                break;
            case R.id.fab2_4:
                jump(10018);
                break;
            case R.id.fab2_5:
                jump(10017);
                break;
            case R.id.fab2_6:
                jump(10016);
                break;
            case R.id.fab2_7:
                jump(10015);
                break;
            case R.id.fab3_1:
                jump(10014);
                break;
            case R.id.fab3_2:
                jump(10013);
                break;
            case R.id.fab3_3:
                jump(10012);
                break;
            case R.id.fab3_4:
                jump(10011);
                break;
            case R.id.fab3_5:
                jump(10010);
                break;
            case R.id.fab3_6:
                jump(10009);
                break;
            case R.id.fab3_7:
                jump(10008);
                break;
            case R.id.fab4_1:
                jump(10007);
                break;
            case R.id.fab4_2:
                jump(10006);
                break;
            case R.id.fab4_3:
                jump(10005);
                break;
            case R.id.fab4_4:
                jump(10004);
                break;
            case R.id.fab4_5:
                jump(10003);
                break;
            case R.id.fab4_6:
                jump(10002);
                break;
            case R.id.fab4_7:
                jump(10001);
                break;

        }
    }

    private void jump(int position) {
        loadData(position);
        fabState(false);
    }
}
