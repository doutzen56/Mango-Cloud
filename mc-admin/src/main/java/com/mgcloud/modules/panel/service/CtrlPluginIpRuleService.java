package com.mgcloud.modules.panel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.panel.entity.CtrlPluginIpRuleEntity;

import java.util.Map;

/**
 * IP限制
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
public interface CtrlPluginIpRuleService extends IService<CtrlPluginIpRuleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

