package com.yoler.potato.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yoler.potato.R;
import com.yoler.potato.request.ConsiliaDetailReq;
import com.yoler.potato.request.ConsiliaDetailReqContent;
import com.yoler.potato.response.ConsiliaDetailResp;
import com.yoler.potato.response.ConsiliaDetailRespContent;
import com.yoler.potato.util.ActivityUtil;
import com.yoler.potato.util.Constant;
import com.yoler.potato.util.GsonUtil;
import com.yoler.potato.util.LogUtil;
import com.yoler.potato.util.MyOkHttpUtil;
import com.yoler.potato.util.ToastUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ConsiliaDetailActivity extends BaseActivity {
    private TextView tvTitle;
    private TextView tvPpatientName;
    private TextView tvPatientSex;
    private TextView tvPatientBirthday;
    private TextView tvPatientZodiac;
    private TextView tvPatienPulse;
    private TextView tvPatientTongue;
    private TextView tvPrescriptionDetail;
    private TextView tvPrescriptionCount;
    private TextView tvPrescriptionMethod;
    private TextView tvPrescriptionDuration;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_consilia_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvPpatientName = (TextView) findViewById(R.id.tv_patient_name);
        tvPatientSex = (TextView) findViewById(R.id.tv_patient_sex);
        tvPatientBirthday = (TextView) findViewById(R.id.tv_patient_birthday);
        tvPatientZodiac = (TextView) findViewById(R.id.tv_patient_zodiac);
        tvPatienPulse = (TextView) findViewById(R.id.tv_patient_pulse);
        tvPatientTongue = (TextView) findViewById(R.id.tv_patient_tongue);
        tvPrescriptionDetail = (TextView) findViewById(R.id.tv_prescription_detail);
        tvPrescriptionCount = (TextView) findViewById(R.id.tv_prescription_count);
        tvPrescriptionMethod = (TextView) findViewById(R.id.tv_prescription_method);
        tvPrescriptionDuration = (TextView) findViewById(R.id.tv_prescription_duration);
        tvTitle.setText(getResources().getText(R.string.consilia_detail_title));
        String patientConditionId = ActivityUtil.getIntentStringParams(mActivity, savedInstanceState, "patientConditionId");
        getConsiliaDetailDatas(patientConditionId);
    }

    @Override
    public void onClick(View view) {

    }

    private void getConsiliaDetailDatas(String patientConditionId) {
        final ConsiliaDetailReq consiliaDetailReq = new ConsiliaDetailReq();
        final ConsiliaDetailReqContent consiliaDetailReqContent = new ConsiliaDetailReqContent();
        consiliaDetailReqContent.setPatientConditionId(patientConditionId);
        consiliaDetailReq.setContent(consiliaDetailReqContent);
        consiliaDetailReq.setOs("Android");
        consiliaDetailReq.setPhone("15311496135");
        consiliaDetailReq.setVersion("V1.0");
        MyOkHttpUtil.postAsync(Constant.GET_CONSILIA_DETAIL, GsonUtil.objectToJson(consiliaDetailReq), new Callback() {
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
            public void onResponse(Call call, final Response response) throws IOException {
                String resp = response.body().string();
                final ConsiliaDetailResp consiliaDetailResp = GsonUtil.jsonToObject(resp, ConsiliaDetailResp.class);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ConsiliaDetailRespContent consiliaDetailRespContent = consiliaDetailResp.getContent();
                        tvPpatientName.setText(consiliaDetailRespContent.getName());
                        tvPatientSex.setText(consiliaDetailRespContent.getSex());
                        tvPatientBirthday.setText(consiliaDetailRespContent.getBirthday());
                        tvPatientZodiac.setText(consiliaDetailRespContent.getZodiac());
                        StringBuffer pulse = new StringBuffer();
                        pulse.append("左寸：");
                        pulse.append(consiliaDetailRespContent.getLeftPulseCun());
                        pulse.append("   ");
                        pulse.append("左关：");
                        pulse.append(consiliaDetailRespContent.getLeftPulseGuan());
                        pulse.append("   ");
                        pulse.append("左尺：");
                        pulse.append(consiliaDetailRespContent.getLeftPulseChi());
                        pulse.append("\r\n");
                        pulse.append("右寸：");
                        pulse.append(consiliaDetailRespContent.getRightPulseCun());
                        pulse.append("   ");
                        pulse.append("右关：");
                        pulse.append(consiliaDetailRespContent.getRightPulseGuan());
                        pulse.append("   ");
                        pulse.append("右尺：");
                        pulse.append(consiliaDetailRespContent.getRightPulseChi());
                        tvPatienPulse.setText(pulse.toString());
                        tvPatientTongue.setText(consiliaDetailRespContent.getTongue());
                        tvPrescriptionDetail.setText(consiliaDetailRespContent.getPrescriptionDetail());
                        tvPrescriptionCount.setText(consiliaDetailRespContent.getPrescriptionCount());
                        tvPrescriptionMethod.setText(consiliaDetailRespContent.getPrescriptionMethod());
                        tvPrescriptionDuration.setText(consiliaDetailRespContent.getPrescriptionDuration());
                    }
                });
            }
        });

    }
}
