package com.yoler.potato.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.yoler.potato.R;

/**
 * Activity父类
 *
 * @author zhangyu
 */
public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {
    protected AppCompatActivity mActivity;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        findViews();
        mActivity = this;
        ivBack = (ImageView) findViewById(R.id.iv_back);
        if (ivBack != null) {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getLayoutResource();

    protected abstract void findViews();

}
