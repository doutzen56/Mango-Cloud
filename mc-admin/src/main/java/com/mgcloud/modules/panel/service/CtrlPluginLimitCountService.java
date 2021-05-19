package com.mgcloud.modules.panel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.panel.entity.CtrlPluginLimitCountEntity;

import java.util.Map;

/**
 * 访问速率限制
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
public interface CtrlPluginLimitCountService extends IService<CtrlPluginLimitCountEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

