package com.zcc.springleran.xxl_job;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class SampleXxlJob {

    private static final Logger log = LoggerFactory.getLogger(SampleXxlJob.class);

    @XxlJob("demoJobHandler2")
    public void demoJobHandler() throws InterruptedException {
    log.info("-----");
    }
}

