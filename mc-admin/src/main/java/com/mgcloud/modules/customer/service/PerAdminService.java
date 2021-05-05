package com.mgcloud.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.customer.entity.PerAdminEntity;

import java.util.Map;

/**
 * 账户列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-04 15:45:23
 */
public interface PerAdminService extends IService<PerAdminEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

