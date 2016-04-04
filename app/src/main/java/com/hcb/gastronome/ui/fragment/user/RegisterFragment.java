package com.hcb.gastronome.ui.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.presenter.UserPresenter;
import com.hcb.gastronome.mvp.view_controller.UserView;
import com.hcb.gastronome.ui.activity.MainActivity;
import com.hcb.gastronome.ui.base.BaseFragment;
import com.hcb.gastronome.util.RegexUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huchuanbin on 16/4/2.
 */
public class RegisterFragment extends BaseFragment implements UserView {
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etCode)
    EditText etCode;
    @Bind(R.id.etPwd)
    EditText etPwd;
    @Bind(R.id.tvGetCode)
    TextView tvGetCode;
    @Bind(R.id.tvRegister)
    TextView tvRegister;
    @Inject
    UserPresenter userPresenter;

    public static RegisterFragment getInstance() {
        return new RegisterFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initInjector(this).inject(this);
        userPresenter.setControllerView(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_register;
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

    @OnClick(R.id.tvRegister)
    public void register() {
        if (!isRightRuler())
            return;
//        ProgressBarHelper.getInstance(false).show(getActivity(), "登录中");
        userPresenter.register(etPhone.getText().toString(), etCode.getText().toString(), etPwd.getText().toString());
    }

    private boolean isRightRuler() {
        {
            if (!RegexUtils.checkMobile(etPhone.getText())) {
                Toast.makeText(getContext(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (etPwd.getText().length() < 6 || etPwd.getText().length() > 16) {
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
        if (type == 0) {
            Observable.create(new Observable.OnSubscribe<Integer>() {
                @Override
                public void call(Subscriber<? super Integer> subscriber) {
                    tvGetCode.setClickable(false);
                    for (int i = 0; i < 61; i++) {
                        subscriber.onNext(i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    subscriber.onCompleted();
                }
            })
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Integer>() {
                        @Override
                        public void onCompleted() {
                            tvGetCode.setText("获取验证码");
                            tvGetCode.setClickable(true);
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(Integer integer) {
                            tvGetCode.setText(integer.toString());
                        }
                    });
        } else if (type == 1) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            getContext().startActivity(intent);
        }
    }
}
