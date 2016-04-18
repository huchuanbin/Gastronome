package com.hcb.gastronome.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.hcb.gastronome.di.ContextLevel;
import com.hcb.gastronome.mvp.model.bmob._User;
import com.hcb.gastronome.mvp.view_controller.UserView;
import com.hcb.gastronome.ui.activity.MainActivity;
import com.trello.rxlifecycle.FragmentLifecycleProvider;

import javax.inject.Inject;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.ResetPasswordByCodeListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by huchuanbin on 16/4/2.
 */
public class UserPresenter extends BasePresenter<UserView> {
    private Context context;
    private FragmentLifecycleProvider fragmentLifecycleProvider;

    @Inject
    public UserPresenter(@ContextLevel(ContextLevel.FRAGMENT) Context context, FragmentLifecycleProvider fragmentLifecycleProvider) {
        this.context = context;
        this.fragmentLifecycleProvider = fragmentLifecycleProvider;
    }

    public void getCode(String phone) {
        BmobSMS.requestSMSCode(context, phone, "Gastronome", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                getControllerView().result(0, true, "发送成功");
            }
        });
    }

    public void register(String phone, String code, String password) {
        _User user = new _User();
        user.setMobilePhoneNumber(phone);
        user.setPassword(password);
        user.signOrLogin(context, code, new SaveListener() {
            @Override
            public void onSuccess() {
                getControllerView().result(1, true, "注册成功");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("UserPresenter", s);
            }
        });
    }

    public void resetPassword(String phone,String code,String newpassword){
        _User.resetPasswordBySMSCode(context, code, newpassword, new ResetPasswordByCodeListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Toast.makeText(context, "密码重置成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
                else {
                    Toast.makeText(context, "密码重置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(String phone, String password) {
        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(phone);
        bmobUser.setPassword(password);
        bmobUser.login(context, new SaveListener() {
            @Override
            public void onSuccess() {
                getControllerView().result(2, true, "登录成功");
                Log.d("UserPresenter", "登录成功");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("UserPresenter", s);
                Log.d("UserPresenter", "i:" + i);
            }
        });
    }
}
