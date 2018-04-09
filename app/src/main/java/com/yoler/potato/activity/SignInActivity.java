package com.yoler.potato.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yoler.potato.R;
import com.yoler.potato.request.SignInReq;
import com.yoler.potato.request.SignInReqContent;
import com.yoler.potato.util.Host;
import com.yoler.potato.util.GsonUtil;
import com.yoler.potato.util.LogUtil;
import com.yoler.potato.util.MyOkHttpUtil;
import com.yoler.potato.util.RegexUtils;
import com.yoler.potato.util.StringUtil;
import com.yoler.potato.util.ToastUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 登录
 * Created by zhangyu on 2017/12/4.
 */

public class SignInActivity extends BaseActivity {
    private Button btnSignIn;
    private EditText etUserName;
    private EditText etPassword;
    private TextView tvUserNameHint;
    private TextView tvPasswordHint;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void findViews() {
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvUserNameHint = (TextView) findViewById(R.id.tv_user_name_hint);
        tvPasswordHint = (TextView) findViewById(R.id.tv_password_hint);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnSignIn.getId()) {
            if (checkInput()) {
                finish();
            }
        }
    }

    private boolean checkInput() {
        {
            String userName = etUserName.getText().toString();
            String password = etPassword.getText().toString();
            if (StringUtil.isEmpty(userName)) {
                tvUserNameHint.setText("请输入用户名");
                tvUserNameHint.setVisibility(View.VISIBLE);
                return false;
            } else if (!RegexUtils.checkEngNum_(userName)) {
                tvUserNameHint.setText("用户名只能包含数字、字母、下划线");
                tvUserNameHint.setVisibility(View.VISIBLE);
                return false;
            } else {
                tvUserNameHint.setVisibility(View.GONE);
            }

            if (StringUtil.isEmpty(password)) {
                tvPasswordHint.setText("请输入密码");
                tvPasswordHint.setVisibility(View.VISIBLE);
                return false;
            } else if (!RegexUtils.checkEngNum_(password)) {
                tvPasswordHint.setText("密码只能包含数字、字母、下划线");
                tvPasswordHint.setVisibility(View.VISIBLE);
                return false;
            } else {
                tvPasswordHint.setVisibility(View.GONE);
            }
            return true;
        }

    }

    private void signInValidate(String userName, String password) {
        SignInReq signInReq = new SignInReq();
        SignInReqContent signInReqContent = new SignInReqContent();
        signInReqContent.setUserName(userName);
        signInReqContent.setPassword(password);
        signInReq.setOs("Android");
        signInReq.setPhone("15311496135");
        signInReq.setVersion("V1.0");
        MyOkHttpUtil.postAsync(Host.GET_CONSILIA_DETAIL, GsonUtil.objectToJson(signInReq), new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(mActivity, getResources().getString(R.string.load_fail));
                        LogUtil.e(e.getMessage(), e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
