package com.yoler.potato.response;

import java.util.List;

/**
 * Created by zhangyu on 2017/11/10.
 */

public class ConsiliaDateIntroResp extends BaseResp {
    private List<ConsiliaDateIntroRespContent> content;

    public List<ConsiliaDateIntroRespContent> getContent() {
        return content;
    }

    public void setContent(List<ConsiliaDateIntroRespContent> content) {
        this.content = content;
    }
}
