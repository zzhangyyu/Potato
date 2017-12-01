package com.yoler.potato.response;

import java.util.List;

/**
 * @author zhangyu
 */

public class UserFavouritePatientResp extends BaseResp {
    private List<UserFavouritePatientRespContent> content;

    public List<UserFavouritePatientRespContent> getContent() {
        return content;
    }

    public void setContent(List<UserFavouritePatientRespContent> content) {
        this.content = content;
    }
}
