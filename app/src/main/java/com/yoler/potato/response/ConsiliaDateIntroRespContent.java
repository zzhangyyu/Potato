package com.yoler.potato.response;

import java.util.List;

/**
 * Created by zhangyu on 2017/11/10.
 */

public class ConsiliaDateIntroRespContent {
    private String visitingDate;
    private List<ConsiliaDateIntroRespPI> patientInfos;

    public String getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(String visitingDate) {
        this.visitingDate = visitingDate;
    }

    public List<ConsiliaDateIntroRespPI> getPatientInfos() {
        return patientInfos;
    }

    public void setPatientInfos(List<ConsiliaDateIntroRespPI> patientInfos) {
        this.patientInfos = patientInfos;
    }
}
