package com.yoler.potato.response;

import java.util.List;

/**
 * Created by zhangyu on 2017/11/10.
 */

public class DateDirResp extends BaseResp {
    private List<DateDirRespContent> content;

    public List<DateDirRespContent> getContent() {
        return content;
    }

    public void setContent(List<DateDirRespContent> content) {
        this.content = content;
    }
}
