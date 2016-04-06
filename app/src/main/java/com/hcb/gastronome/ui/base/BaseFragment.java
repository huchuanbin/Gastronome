package com.hcb.gastronome.ui.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.gastronome.AppConfig;
import com.hcb.gastronome.di.component.DaggerFragmentComponent;
import com.hcb.gastronome.di.component.FragmentComponent;
import com.hcb.gastronome.di.modules.FragmentModule;
import com.trello.rxlifecycle.FragmentLifecycleProvider;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by huchuanbin on 16/3/30.
 */
public abstract class BaseFragment extends RxFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);

        ButterKnife.bind(this, view);
        initView(savedInstanceState);
        return view;
    }

    public FragmentComponent initInjector(FragmentLifecycleProvider fragmentLifecycleProvider) {
        return DaggerFragmentComponent.builder()
                .applicationComponent(AppConfig.getInstance().getApplicationComponent())
                .fragmentModule(new FragmentModule(fragmentLifecycleProvider))
                .build();
    }

    protected abstract int getLayout();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void adaptStatusBar(View view) {
        StatusBarCompat.translucentStatusBar(getActivity());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && view != null) {
            view.setPadding(0, StatusBarCompat.getStatusBarHeight(getActivity()), 0, 0);
            view.requestLayout();
        }
    }

}
