package com.jxnu.fundCrawler.business.model.protocol;

import com.jxnu.fundCrawler.business.model.http.HttpPropers;

/**
 * Created by coder on 2/04/17.
 */
public class MakeShareReq extends HttpPropers {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
