package com.sample.security.core.support;

/**
 * @author xuWeiJia
 * @date 2020/1/6
 */
public class SimpleResponse {

    public SimpleResponse(Object content){
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
