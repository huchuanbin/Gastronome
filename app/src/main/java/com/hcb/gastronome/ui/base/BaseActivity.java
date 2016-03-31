package com.hcb.gastronome.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.hcb.gastronome.AppConfig;
import com.hcb.gastronome.di.component.ActivityComponent;
import com.hcb.gastronome.di.component.DaggerActivityComponent;
import com.hcb.gastronome.di.modules.ActivityModule;
import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;

/**
 * Created by huchuanbin on 16/3/29.
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        setStatusBarDarkMode(true, this);
        init(savedInstanceState);
    }
    public abstract int getLayout();

    public ActivityComponent initInjetor(ActivityLifecycleProvider activityLifecycleProvider){
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(AppConfig.getInstance().getApplicationComponent())
                .activityModule(new ActivityModule(activityLifecycleProvider))
                .build();
        return activityComponent;
    }


    public void init(Bundle savedInstanceState) {

    }

    private void setStatusBarDarkMode(boolean darkMode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkMode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
