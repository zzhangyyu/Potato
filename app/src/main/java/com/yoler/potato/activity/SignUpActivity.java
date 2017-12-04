package com.yoler.potato.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yoler.potato.R;

/**
 * Created by zhangyu on 2017/12/4.
 */

public class SignUpActivity extends BaseActivity {
    private Button btnSignUp;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void findViews() {
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnSignUp.getId()) {
            finish();
        }
    }
}
