package com.mgcloud.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.customer.entity.ClustersEntity;

import java.util.Map;

/**
 * 集群列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-05 15:21:32
 */
public interface ClustersService extends IService<ClustersEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void saveCluster(ClustersEntity entity);
    void updateCluster(ClustersEntity entity);
    void deleteBatch(Integer[] ids);
}

