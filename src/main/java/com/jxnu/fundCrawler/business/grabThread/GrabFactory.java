package com.jxnu.fundCrawler.business.grabThread;

import com.jxnu.fundCrawler.business.grabThread.specific.CompanyGrab;
import com.jxnu.fundCrawler.business.grabThread.specific.FundGrab;
import com.jxnu.fundCrawler.business.grabThread.specific.FundIndexGrab;
import com.jxnu.fundCrawler.business.grabThread.specific.FundNetWorthGrab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class GrabFactory {
    @Value("${company.switch}")
    private Integer companySwitch;
    @Value("${fund.switch}")
    private Integer fundSwitch;
    @Value("${fundNetWorth.switch}")
    private Integer fundNetWorthSwitch;
    @Autowired
    private CompanyGrab companyGrab;
    @Autowired
    private FundGrab fundGrab;
    @Autowired
    private FundNetWorthGrab fundNetWorthGrab;
    @Autowired
    private FundIndexGrab fundIndexGrab;

    @PostConstruct
    public void init() {
        if (companySwitch == 1) {
            ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(companyGrab, companySwitch), 0, 6, TimeUnit.HOURS);
        }

        if (fundSwitch == 1) {
            ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundGrab, fundSwitch), 0, 6, TimeUnit.HOURS);
        }


        ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundNetWorthGrab, fundNetWorthSwitch), 0, 2, TimeUnit.HOURS);

        ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundIndexGrab, companySwitch), 0, 2, TimeUnit.HOURS);

    }

}
