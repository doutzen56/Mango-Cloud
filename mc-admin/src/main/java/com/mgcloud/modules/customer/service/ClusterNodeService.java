package com.mgcloud.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.customer.entity.ClusterNodeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-06 10:38:24
 */
public interface ClusterNodeService extends IService<ClusterNodeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    /**
     * 根据集群ID查询所属节点ID
     * @param id 集群ID
     * @return
     */
    List<Integer> queryById(Integer id);

    /**
     * 根据集群ID逻辑删除
     * @param clusterId
     * @return
     */
    int deleteBatch(Integer[] clusterIds);

    /**
     * 保存集群与节点之间的关系
     * @param clusterId 集群ID
     * @param nodeIdList 节点ID集合
     */
    void saveOrUpdate(Integer clusterId,List<Integer> nodeIdList);
}

