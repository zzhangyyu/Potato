package com.yoler.potato.response;

import java.util.List;

/**
 * @author zhangyu
 */

public class PatientDirResp extends BaseResp {
    private List<PatientDirRespContent> content;

    public List<PatientDirRespContent> getContent() {
        return content;
    }

    public void setContent(List<PatientDirRespContent> content) {
        this.content = content;
    }
}
