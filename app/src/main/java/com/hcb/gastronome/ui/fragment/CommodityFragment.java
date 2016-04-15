package com.hcb.gastronome.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.TradeService;
import com.alibaba.sdk.android.trade.callback.TradeProcessCallback;
import com.alibaba.sdk.android.trade.model.TradeResult;
import com.alibaba.sdk.android.trade.page.ItemDetailPage;
import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.bmob.Commondity;
import com.hcb.gastronome.mvp.model.home.BannerData;
import com.hcb.gastronome.mvp.presenter.CommodityPresenter;
import com.hcb.gastronome.mvp.view_controller.CommodityView;
import com.hcb.gastronome.ui.activity.MenuActivity;
import com.hcb.gastronome.ui.adapter.CommodityAdapter;
import com.hcb.gastronome.ui.base.BaseFragment;
import com.hcb.gastronome.ui.base.BaseRecycleFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by huchuanbin on 16/3/30.
 */
public class CommodityFragment extends BaseRecycleFragment implements CommodityView {
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Inject
    CommodityPresenter commodityPresenter;
    @Inject
    CommodityAdapter commodityAdapter;

    public static CommodityFragment getInstance() {
        return new CommodityFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initInjector(this).inject(this);
        commodityPresenter.setControllerView(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(linearLayoutManager);
        rvList.setAdapter(commodityAdapter);
        loadData(1);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_commodity;
    }

    @Override
    public void loadData(int cid) {
        commodityPresenter.getCommodity();
    }

    @Override
    public void loadServerData(List<Commondity> commondity) {
        commodityAdapter.autoInsertItems(commondity);
        commodityAdapter.setOnItemClickListener((adapterView, view, i, l) -> interfaceJump(commondity.get(i).getUrl()));
    }

    private void interfaceJump(String url) {
        TradeService tradeService = AlibabaSDK.getService(TradeService.class);
        ItemDetailPage itemDetailPage = new ItemDetailPage(url, null);
//        TaokeParams taokeParams = new TaokeParams(); //若非淘客taokeParams设置为null即可
//        taokeParams.pid = "mm_28xxxx4_4xxxx71_151xxxxx5";
        tradeService.show(itemDetailPage, null, getActivity(), null, new TradeProcessCallback() {

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(getContext(), "失败 " + code + msg,
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPaySuccess(TradeResult tradeResult) {
                Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT)
                        .show();

            }
        });
//        Intent intent = new Intent(getContext(), MenuActivity.class);
//        intent.putExtra("id", id);
//        intent.putExtra("title", title);
//        intent.putExtra("albums", albums);
//        getActivity().startActivity(intent);
    }
}