package com.yoler.potato.response;

/**
 * Created by zhangyu on 2017/11/10.
 */

public class ConsiliaPatientIntroRespContent {
    private String visitingDate;
    private String patientInfoId;
    private String patientConditionId;

    public String getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(String visitingDate) {
        this.visitingDate = visitingDate;
    }

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
}
