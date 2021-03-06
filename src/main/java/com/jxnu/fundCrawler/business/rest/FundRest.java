package com.jxnu.fundCrawler.business.rest;

import com.google.common.eventbus.Subscribe;
import com.jxnu.fundCrawler.business.model.protocol.*;
import com.jxnu.fundCrawler.business.store.AttentionFundStore;
import com.jxnu.fundCrawler.http.annotation.HttpHander;
import com.jxnu.fundCrawler.http.annotation.RequestMap;
import com.jxnu.fundCrawler.utils.ResponseUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by coder on 2017-03-19.
 */
@Component
@HttpHander
public class FundRest {
    @Resource
    private AttentionFundStore attentionFundStore;


    /**
     * 根据基金代码查询基金净值
     *
     * @param req
     */
    @Subscribe
    @RequestMap(url = "/rest/fundList", encode = "json", Class = FundReq.class)
    public void fundName(FundReq req) {
        FundResp resp = new FundResp();
        resp.setAttentionFundList(attentionFundStore.queryAll());
        ResponseUtils.response(req, resp);
    }

    /**
     * 获取基金的
     *
     * @param req
     */
    @Subscribe
    @RequestMap(url = "/rest/makeshare", encode = "json", Class = MakeShareReq.class)
    public void makeShare(MakeShareReq req) {
        MakeShareResp resp = new MakeShareResp();
        resp.setMakeShareList(attentionFundStore.queryFundMakeShare());
        resp.setMax(attentionFundStore.queryMaxMakeShare());
        ResponseUtils.response(req, resp);
    }
}
