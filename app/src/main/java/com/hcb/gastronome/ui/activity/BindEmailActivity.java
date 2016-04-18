package com.hcb.gastronome.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.bmob._User;
import com.hcb.gastronome.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.listener.EmailVerifyListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by huchuanbin on 16/4/15.
 */
public class BindEmailActivity extends BaseActivity {
    @Bind(R.id.app_bar)
    AppBarLayout barLayout;
    @Bind(R.id.etEmail)
    EditText etEmail;
    @Bind(R.id.tvBindEmail)
    TextView tvBindEmail;
    private String email;

    @Override
    public int getLayout() {
        return R.layout.activity_bind_email;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        adaptStatusBar(barLayout);
        initToolBar("绑定邮箱");
    }

    @OnClick(R.id.tvBindEmail)
    public void bindEmail() {
        email = etEmail.getText().toString();
        _User user = new _User();
        user.setEmail(email);
        user.update(this,MainActivity.userId, new UpdateListener() {
            @Override
            public void onSuccess() {
                _User.requestEmailVerify(BindEmailActivity.this, email, new EmailVerifyListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(BindEmailActivity.this, "请求验证邮件成功，请到" + email +
                                "邮箱中进行激活。", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(BindEmailActivity.this, "请求验证邮件失败:" + s
                                , Toast.LENGTH_SHORT).show();
                        Log.d("BindEmailActivity", i + s);
                    }
                });
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("BindEmailActivity", i + s);
            }
        });


    }
}
