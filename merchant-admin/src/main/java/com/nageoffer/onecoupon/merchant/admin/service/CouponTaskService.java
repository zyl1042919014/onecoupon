package com.nageoffer.onecoupon.merchant.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.onecoupon.merchant.admin.dao.entity.CouponTaskDO;
import com.nageoffer.onecoupon.merchant.admin.dto.req.CouponTaskCreateReqDTO;

public interface CouponTaskService extends IService<CouponTaskDO> {

    void createCouponTask(CouponTaskCreateReqDTO requestParam);
}
