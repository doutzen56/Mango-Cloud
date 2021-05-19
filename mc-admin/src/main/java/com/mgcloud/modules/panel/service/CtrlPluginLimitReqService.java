package com.mgcloud.modules.panel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.panel.entity.CtrlPluginLimitReqEntity;

import java.util.Map;

/**
 * 限制请求速度
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
public interface CtrlPluginLimitReqService extends IService<CtrlPluginLimitReqEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

