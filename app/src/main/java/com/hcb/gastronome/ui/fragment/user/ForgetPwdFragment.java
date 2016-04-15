package com.hcb.gastronome.ui.fragment.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.presenter.UserPresenter;
import com.hcb.gastronome.mvp.view_controller.UserView;
import com.hcb.gastronome.ui.base.BaseFragment;
import com.hcb.gastronome.util.RegexUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by huchuanbin on 16/4/15.
 */
public class ForgetPwdFragment extends BaseFragment implements UserView {
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etCode)
    EditText etCode;
    @Bind(R.id.etNewPwd)
    EditText etNewPwd;
    @Bind(R.id.tvGetCode)
    TextView tvGetCode;
    @Bind(R.id.tvResetPwd)
    TextView tvForgetPwd;
    @Inject
    UserPresenter userPresenter;

    public static ForgetPwdFragment getInstance() {
        return new ForgetPwdFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_forget_pwd;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initInjector(this).inject(this);
        userPresenter.setControllerView(this);
    }

    @OnClick(R.id.tvGetCode)
    public void getCode() {
        if (!RegexUtils.checkMobile(etPhone.getText())) {
            Toast.makeText(getContext(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        } else {
            userPresenter.getCode(etPhone.getText().toString());
            Log.d("RegisterFragment", etPhone.getText().toString());
        }
    }

    @OnClick(R.id.tvResetPwd)
    public void forgetPwd() {
        if (!isRightRuler())
            return;
        userPresenter.resetPassword(etPhone.getText().toString(), etCode.getText().toString(), etNewPwd.getText().toString());
    }

    private boolean isRightRuler() {
        {
            if (!RegexUtils.checkMobile(etPhone.getText())) {
                Toast.makeText(getContext(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (etNewPwd.getText().length() < 6 || etNewPwd.getText().length() > 16) {
                Toast.makeText(getContext(), "请输入6-16位密码", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(etCode.getText())) {
                Toast.makeText(getContext(), "验证码有误", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }

    @Override
    public void result(int type, boolean success, String msg) {

    }
}
