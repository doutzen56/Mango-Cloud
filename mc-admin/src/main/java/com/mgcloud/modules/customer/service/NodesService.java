package com.mgcloud.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.customer.entity.NodesEntity;

import java.util.Map;

/**
 * 节点列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-05 15:21:32
 */
public interface NodesService extends IService<NodesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

