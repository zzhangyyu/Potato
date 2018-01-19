package com.yoler.potato.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yoler.potato.R;
import com.yoler.potato.util.RegexUtils;
import com.yoler.potato.util.StringUtil;

/**
 * 注册
 * Created by zhangyu on 2017/12/4.
 */

public class SignUpActivity extends BaseActivity {
    private Button btnSignUp;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etEnsurePassword;
    private TextView tvUserNameHint;
    private TextView tvPasswordHint;
    private TextView tvEnsurePasswordHint;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void findViews() {
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        etEnsurePassword = (EditText) findViewById(R.id.et_ensure_password);
        tvUserNameHint = (TextView) findViewById(R.id.tv_user_name_hint);
        tvPasswordHint = (TextView) findViewById(R.id.tv_password_hint);
        tvEnsurePasswordHint = (TextView) findViewById(R.id.tv_ensure_password_hint);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvUserNameHint.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvPasswordHint.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etEnsurePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvEnsurePasswordHint.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnSignUp.getId()) {
            if (checkInput()) {
                finish();
            }
        }
    }

    private boolean checkInput() {
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        String ensurePassword = etEnsurePassword.getText().toString();
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

        if (StringUtil.isEmpty(ensurePassword)) {
            tvEnsurePasswordHint.setText("请再次输入密码");
            tvEnsurePasswordHint.setVisibility(View.VISIBLE);
            return false;
        } else if (!password.equals(ensurePassword)) {
            tvEnsurePasswordHint.setText("两次密码不一致");
            tvEnsurePasswordHint.setVisibility(View.VISIBLE);
            return false;
        } else {
            tvEnsurePasswordHint.setVisibility(View.GONE);
        }
        return true;
    }


}
