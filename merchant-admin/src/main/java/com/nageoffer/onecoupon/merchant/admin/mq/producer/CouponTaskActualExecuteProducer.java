package com.nageoffer.onecoupon.merchant.admin.mq.producer;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.nageoffer.onecoupon.merchant.admin.mq.base.BaseSendExtendDTO;
import com.nageoffer.onecoupon.merchant.admin.mq.base.MessageWrapper;
import com.nageoffer.onecoupon.merchant.admin.mq.event.CouponTaskExecuteEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class CouponTaskActualExecuteProducer extends AbstractCommonSendProduceTemplate<CouponTaskExecuteEvent>{


    private final ConfigurableEnvironment environment;

    public CouponTaskActualExecuteProducer(@Autowired RocketMQTemplate rocketMQTemplate, @Autowired ConfigurableEnvironment environment) {
        super(rocketMQTemplate);
        this.environment = environment;
    }


    @Override
    protected BaseSendExtendDTO buildBaseSendExtendParam(CouponTaskExecuteEvent messageSendEvent) {
        return BaseSendExtendDTO.builder()
                .eventName("优惠券推送执行")
                .keys(String.valueOf(messageSendEvent.getCouponTaskId()))
                .topic(environment.resolvePlaceholders("one-coupon_distribution-service_coupon-task-execute_topic${unique-name:}"))
                .sentTimeout(2000L)
                .build();
    }

    @Override
    protected Message<?> buildMessage(CouponTaskExecuteEvent couponTaskExecuteEvent, BaseSendExtendDTO requestParam) {
        String keys = StrUtil.isEmpty(requestParam.getKeys()) ? UUID.randomUUID().toString() : requestParam.getKeys();
        return MessageBuilder
                .withPayload(new MessageWrapper(keys, couponTaskExecuteEvent))
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, requestParam.getTag())
                .build();
    }
}
