package com.zcc.springleran.rocket;

/**
 * @author <a href="#">allen</a>
 * @version v1.0
 * @since qwe.java v1.0 2024年10月31日 19:16 allen
 */

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "topic-AB",consumerGroup = "${rocketmq.consumer.group}",selectorExpression = "*")
public class RocketMQCustomerTest implements RocketMQListener<MessageExt> {

    @Value("rocketmq.consumer.group")
    String consumerGroup;
    @Override
    public void onMessage(MessageExt message) {
        System.out.println("*******consumerGroup****"+consumerGroup);
        System.out.println("------message----"+message);
        System.out.println("");

    }
}
