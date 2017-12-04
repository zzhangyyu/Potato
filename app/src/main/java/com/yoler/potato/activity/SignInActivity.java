package com.yoler.potato.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yoler.potato.R;

/**
 * Created by zhangyu on 2017/12/4.
 */

public class SignInActivity extends BaseActivity {
    private Button btnSignIn;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void findViews() {
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnSignIn.getId()) {
            finish();
        }
    }
}
