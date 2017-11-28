package com.yoler.potato.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.yoler.potato.R;
import com.yoler.potato.fragment.ConsiliaDateDirFragment;
import com.yoler.potato.fragment.ConsiliaPatientDirFragment;
import com.yoler.potato.util.MeasureUtil;
import com.yoler.potato.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {
    private static Boolean isExit = false;
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView bottomNavigationView;
    private int lastShowFragmentIndex = 0;
    private List<Fragment> fragments = new ArrayList<>();
    private int mDrawerWidth;//抽屉全部拉出来时的宽度
    private LinearLayout vContent;
    private LinearLayout vDrawer;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.v_home_navigation);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        vContent = (LinearLayout) findViewById(R.id.v_content);
        vDrawer = (LinearLayout) findViewById(R.id.v_drawer);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        mDrawerLayout.addDrawerListener(this);

        MeasureUtil.measureView(vDrawer);
        mDrawerWidth = vDrawer.getMeasuredWidth();
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
            transaction.add(R.id.v_home_fragment, fragments.get(index));
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
                .add(R.id.v_home_fragment, consiliaDateDirFragment)
                .show(consiliaDateDirFragment)
                .commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByDoubleClick();  //退出应用的操作
        }
        return false;
    }

    /**
     * 双击退出应用
     */
    private void exitByDoubleClick() {
        if (isExit == false) {
            isExit = true; // 准备退出
            ToastUtil.showToast(mActivity, "再按一次退出程序");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 3000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        vContent.setScrollX((int) (-1 * slideOffset * mDrawerWidth));
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }


}
