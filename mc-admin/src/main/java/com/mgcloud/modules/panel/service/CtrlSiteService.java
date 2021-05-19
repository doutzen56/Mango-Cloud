package com.mgcloud.modules.panel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.panel.entity.CtrlSiteEntity;

import java.util.Map;

/**
 * 站点列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:58
 */
public interface CtrlSiteService extends IService<CtrlSiteEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

