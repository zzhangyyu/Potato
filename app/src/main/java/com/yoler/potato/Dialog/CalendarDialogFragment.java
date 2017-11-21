package com.yoler.potato.Dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.timessquare.CalendarPickerView;
import com.yoler.potato.R;

import java.util.Calendar;
import java.util.Date;


/**
 * 普通dialog
 *
 * @author zhangyu
 */
public class CalendarDialogFragment extends BaseDialogFragment {
    private String dialogTitle;
    private TextView tvDialogTitle;
    private CalendarPickerView cpvCalendar;
    private Button btnCancel;
    private Button btnSure;

    public static CalendarDialogFragment getInstance(FragmentActivity activity, String dialogTitle) {
        CalendarDialogFragment fragment = new CalendarDialogFragment();
        fragment.mActivity = activity;
        fragment.dialogTitle = dialogTitle;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        View view = inflater.inflate(R.layout.dialog_calendar, container);
        tvDialogTitle = (TextView) view.findViewById(R.id.tv_dialog_title);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnSure = (Button) view.findViewById(R.id.btn_sure);
        cpvCalendar = (CalendarPickerView) view.findViewById(R.id.calendar_view);
        //日期控件
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Calendar beforeYear = Calendar.getInstance();
        beforeYear.add(Calendar.YEAR, -1);
        Date today = new Date();
//        cpvCalendar.init(today, nextYear.getTime()).withSelectedDate(today);
        //默认是只选择一个日期，如果想要选择多个日期，使用下面这行代码
        cpvCalendar.init(beforeYear.getTime(), nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE);

        //btn
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        tvDialogTitle.setText(dialogTitle);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogWH(0.9, 0.9);
    }

    @Override
    public void show() {
        show("calendarDialog");
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == btnSure.getId()) {
            this.dismiss();
        } else if (v.getId() == btnCancel.getId()) {
            this.dismiss();
        }
    }
}
