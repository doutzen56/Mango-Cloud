package com.mgcloud.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.customer.entity.CustomerEntity;

import java.util.Map;

/**
 * 客户列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-05 15:21:32
 */
public interface CustomerService extends IService<CustomerEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void saveCustomer(CustomerEntity entity);
    void updateCustomer(CustomerEntity entity);
    void deleteBatch(Integer[] ids);
}

