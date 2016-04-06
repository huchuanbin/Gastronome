package com.hcb.gastronome.ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hcb.gastronome.AppConfig;
import com.hcb.gastronome.R;
import com.hcb.gastronome.di.component.ActivityComponent;
import com.hcb.gastronome.di.component.DaggerActivityComponent;
import com.hcb.gastronome.di.modules.ActivityModule;
import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by huchuanbin on 16/3/29.
 */
public abstract class BaseActivity extends RxAppCompatActivity {


    public ActivityComponent mActivityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        setStatusBarDarkMode(true, this);
        init(savedInstanceState);
    }

    public abstract int getLayout();

    public ActivityComponent initInjector(ActivityLifecycleProvider activityLifecycleProvider) {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(AppConfig.getInstance().getApplicationComponent())
                .activityModule(new ActivityModule(activityLifecycleProvider))
                .build();
        return mActivityComponent;
    }

    public void init(Bundle savedInstanceState) {
    }

    public void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
        }
    }

    public void adaptStatusBar(View view) {
        StatusBarCompat.translucentStatusBar(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && view != null) {
            view.setPadding(0, StatusBarCompat.getStatusBarHeight(this), 0, 0);
            view.requestLayout();
        }
    }

    public void initToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null && !TextUtils.isEmpty(title)) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle("  ");
            if (findViewById(R.id.tv_title) != null)
                ((TextView) findViewById(R.id.tv_title)).setText(title);
        }
        showTitleShadow();
    }

    public void showTitleShadow() {
        View toolBarLine = findViewById(R.id.line_toolbar);
        if (toolBarLine != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toolBarLine.setVisibility(View.GONE);
            } else {
                toolBarLine.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
