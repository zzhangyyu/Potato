package com.yoler.potato.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.timessquare.CalendarPickerView;
import com.yoler.potato.bean.DateDirSelectedDateBean;
import com.yoler.potato.R;
import com.yoler.potato.util.DateFormatUtil;
import com.yoler.potato.util.SPUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private OnEnsureDateSelectListener ensureDateSelectListener;

    public static CalendarDialogFragment getInstance(FragmentActivity activity, String dialogTitle, OnEnsureDateSelectListener ensureDateSelectListener) {
        CalendarDialogFragment fragment = new CalendarDialogFragment();
        fragment.mActivity = activity;
        fragment.dialogTitle = dialogTitle;
        fragment.ensureDateSelectListener = ensureDateSelectListener;
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
        Date today = new Date();
        DateDirSelectedDateBean selectedDateBean = SPUtil.getPro(mActivity, "DateDirSelectedDateBean", DateDirSelectedDateBean.class);
        cpvCalendar.init(DateFormatUtil.addYear(today, -1), DateFormatUtil.addDate(today, +1)).inMode(CalendarPickerView.SelectionMode.RANGE).withSelectedDates(getDateRange(selectedDateBean, today));

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
            List<Date> dateList = cpvCalendar.getSelectedDates();
            ensureDateSelectListener.onEnsureDateSelect(dateList);
            DateDirSelectedDateBean selectedDateBean = new DateDirSelectedDateBean();
            selectedDateBean.setSelectedDateList(dateList);
            SPUtil.putPro(mActivity, "DateDirSelectedDateBean", selectedDateBean);
            this.dismiss();
        } else if (v.getId() == btnCancel.getId()) {
            this.dismiss();
        }
    }

    public interface OnEnsureDateSelectListener {
        void onEnsureDateSelect(List<Date> selectedDates);
    }

    private List<Date> getDateRange(DateDirSelectedDateBean selectedDateBean, Date today) {
        List<Date> result = new ArrayList<>();
        List<Date> selectedDateList = new ArrayList<>();
        if (selectedDateBean != null) {
            selectedDateList = selectedDateBean.getSelectedDateList();
        }
        if (selectedDateList.size() != 0) {
            result.add(selectedDateList.get(0));
            result.add(selectedDateList.get(selectedDateList.size() - 1));
        } else {
            result.add(today);
        }
        return result;
    }
}
