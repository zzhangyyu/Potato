package com.yoler.potato.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.timessquare.CalendarPickerView;
import com.yoler.potato.Dialog.CalendarDialogFragment;
import com.yoler.potato.R;
import com.yoler.potato.fragment.ConsiliaDateDirFragment;
import com.yoler.potato.fragment.ConsiliaPatientDirFragment;
import com.yoler.potato.util.ActivityUtil;
import com.yoler.potato.util.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView bottomNavigationView;
    private int lastShowFragmentIndex = 0;
    private ImageView ivBack;
    private TextView tvCalendar;
    private TextView tvTitle;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvCalendar = (TextView) findViewById(R.id.tv_calendar);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.v_home_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        tvCalendar.setOnClickListener(this);

        tvTitle.setText(getResources().getText(R.string.consilia_date_tile));
        ivBack.setVisibility(View.GONE);
        //Fragment
        initFragments();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == tvCalendar.getId()) {
            CalendarDialogFragment calendarDialogFragment = CalendarDialogFragment.getInstance(mActivity, "请选择日期");
            calendarDialogFragment.setCancelable(true);
            calendarDialogFragment.show();
        }
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

}
