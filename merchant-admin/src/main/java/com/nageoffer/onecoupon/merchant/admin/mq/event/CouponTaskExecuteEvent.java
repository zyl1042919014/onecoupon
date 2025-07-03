package com.nageoffer.onecoupon.merchant.admin.mq.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponTaskExecuteEvent {
    /**
     * 推送任务id
     */
    private Long couponTaskId;
}