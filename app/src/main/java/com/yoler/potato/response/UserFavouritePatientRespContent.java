package com.yoler.potato.response;

public class UserFavouritePatientRespContent {
    private String patientInfoId;
    private String patientName;
    private String patientSex;
    private String consiliaCnt;

    public String getPatientInfoId() {
        return patientInfoId;
    }

    public void setPatientInfoId(String patientInfoId) {
        this.patientInfoId = patientInfoId;
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

    public String getConsiliaCnt() {
        return consiliaCnt;
    }

    public void setConsiliaCnt(String consiliaCnt) {
        this.consiliaCnt = consiliaCnt;
    }
}
