package com.yoler.potato.request;

public class SignInReq extends BaseReq {
    public SignInReqContent content;

    public SignInReqContent getContent() {
        return content;
    }

    public void setContent(SignInReqContent content) {
        this.content = content;
    }
}
