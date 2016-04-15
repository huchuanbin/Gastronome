package com.hcb.gastronome.ui.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hcb.gastronome.R;
import com.hcb.gastronome.ui.activity.UserActivity;
import com.hcb.gastronome.ui.adapter.PageAdapter;
import com.hcb.gastronome.ui.base.BaseFragment;
import com.hcb.gastronome.ui.fragment.HomeFragment;
import com.hcb.gastronome.ui.widget.TabViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class MineFragment extends BaseFragment {
    private PageAdapter adapter;
    private List<BaseFragment> listFragment;
    private List<String> listTitle;
    @Bind(R.id.rlBar)
    RelativeLayout rlBar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tabViewPager)
    TabViewPager tabViewPager;
    @Bind(R.id.tvUserNickname)
    TextView tvUserNickname;
    @Bind(R.id.ivUserProfile)
    ImageView ivUserProfile;

    public static MineFragment getInstance() {
        return new MineFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adaptStatusBar(rlBar);
        listFragment = new ArrayList<>();
        listTitle = new ArrayList<>();
        listFragment.add(CollectionFragment.getInstance());
        listFragment.add(HomeFragment.getInstance());
        listTitle.add("我的收藏");
        listTitle.add("tab 2");
        adapter = new PageAdapter(getChildFragmentManager(), listFragment, listTitle);
        tabViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(tabViewPager);

        ivUserProfile.setOnClickListener(v -> interfaceJump());
    }

    private void interfaceJump() {
        Intent intent = new Intent(getContext(), UserActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }
}
