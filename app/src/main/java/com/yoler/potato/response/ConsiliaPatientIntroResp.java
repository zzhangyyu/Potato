package com.yoler.potato.response;

import java.util.List;

/**
 * Created by zhangyu on 2017/11/10.
 */

public class ConsiliaPatientIntroResp extends BaseResp {
    private List<ConsiliaPatientIntroRespContent> content;

    public List<ConsiliaPatientIntroRespContent> getContent() {
        return content;
    }

    public void setContent(List<ConsiliaPatientIntroRespContent> content) {
        this.content = content;
    }
}
