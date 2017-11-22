package com.yoler.potato.response;

/**
 * Created by zhangyu on 2017/11/10.
 */

public class ConsiliaDateIntroRespPI {
    private String patientInfoId;
    private String patientConditionId;
    private String patientName;
    private String patientSex;

    public String getPatientInfoId() {
        return patientInfoId;
    }

    public void setPatientInfoId(String patientInfoId) {
        this.patientInfoId = patientInfoId;
    }

    public String getPatientConditionId() {
        return patientConditionId;
    }

    public void setPatientConditionId(String patientConditionId) {
        this.patientConditionId = patientConditionId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }
}
