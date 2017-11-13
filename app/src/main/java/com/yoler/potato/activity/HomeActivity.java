package com.yoler.potato.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.yoler.potato.R;
import com.yoler.potato.fragment.ConsiliaDateDirFragment;
import com.yoler.potato.fragment.ConsiliaPatientDirFragment;
import com.yoler.potato.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView bottomNavigationView;
    private int lastShowFragmentIndex = 0;
    private List<Fragment> fragments=new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //底部导航栏
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.v_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //Fragment
        initFragments();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.it_consilia_date_dir:
                if (lastShowFragmentIndex != 0) {
                    switchFrament(lastShowFragmentIndex, 0);
                    lastShowFragmentIndex = 0;
                }
                return true;
            case R.id.it_consilia_patient_dir:
                if (lastShowFragmentIndex != 1) {
                    switchFrament(lastShowFragmentIndex, 1);
                    lastShowFragmentIndex = 1;
                }
                return true;
            case R.id.nearby:
                if (lastShowFragmentIndex != 2) {
                    ToastUtil.showToast(mActivity, "待实现");
                    lastShowFragmentIndex = 2;
                }
                return true;
        }
        return false;
    }


    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    private void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments.get(lastIndex));
        if (!fragments.get(index).isAdded()) {
            transaction.add(R.id.content, fragments.get(index));
        }
        transaction.show(fragments.get(index)).commitAllowingStateLoss();
    }

    /**
     * 初始化Fragment
     */
    private void initFragments() {
        ConsiliaDateDirFragment consiliaDateDirFragment = new ConsiliaDateDirFragment();
        ConsiliaPatientDirFragment consiliaPatientDirFragment = new ConsiliaPatientDirFragment();
        fragments.add(consiliaDateDirFragment);
        fragments.add(consiliaPatientDirFragment);
        fragments.add(consiliaDateDirFragment);
        lastShowFragmentIndex = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, consiliaDateDirFragment)
                .show(consiliaDateDirFragment)
                .commit();
    }

}
