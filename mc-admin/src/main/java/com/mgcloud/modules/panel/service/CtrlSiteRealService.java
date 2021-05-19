package com.mgcloud.modules.panel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.panel.entity.CtrlSiteRealEntity;

import java.util.Map;

/**
 * 源点列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
public interface CtrlSiteRealService extends IService<CtrlSiteRealEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

