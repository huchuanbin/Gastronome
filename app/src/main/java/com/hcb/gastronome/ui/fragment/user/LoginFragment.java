package com.hcb.gastronome.ui.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.hcb.gastronome.R;
import com.hcb.gastronome.event.UserEvent;
import com.hcb.gastronome.mvp.presenter.UserPresenter;
import com.hcb.gastronome.mvp.view_controller.UserView;
import com.hcb.gastronome.ui.activity.MainActivity;
import com.hcb.gastronome.ui.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by huchuanbin on 16/4/2.
 */
public class LoginFragment extends BaseFragment implements UserView {
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etPwd)
    EditText etPwd;
    @Bind(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @Inject
    UserPresenter userPresenter;

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initInjector(this).inject(this);
        userPresenter.setControllerView(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_login;
    }

    @OnClick(R.id.tvLogin)
    public void login() {
        userPresenter.login(etPhone.getText().toString(), etPwd.getText().toString());
    }

    @Override
    public void result(int type, boolean success, String msg) {
        if (type == 2) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            getActivity().startActivity(intent);
        }
    }
    @OnClick(R.id.tv_forget_pwd)
    public void forgetPwd(){
        UserEvent userEvent=new UserEvent();
        userEvent.setForgetPwd(0);
        EventBus.getDefault().post(userEvent);
    }
}
