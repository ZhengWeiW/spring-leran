package com.zcc.springleran.rocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="#">allen</a>
 * @version v1.0
 * @since RocketController.java v1.0 2024年10月31日 20:40 allen
 */
@RestController
@RequestMapping("/rocket")
public class RocketController {
    @Value("${rocketmq.consumer.group}")
    String consumerGroup;
    @Resource
    RocketMQCustomerTest rocketMQCustomerTest;
    @PostMapping("/consumer")
    public void consumer(){
        System.out.println("-------");

    }
}
