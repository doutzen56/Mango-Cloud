package com.mgcloud.modules.customer.dao;

import com.mgcloud.modules.customer.entity.ClusterNodeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-06 10:38:24
 */
@Mapper
public interface ClusterNodeDao extends BaseMapper<ClusterNodeEntity> {
    /**
     * 根据集群ID查询所属节点ID
     * @param clusterId 集群ID
     * @return
     */
    List<Integer> queryById(Integer clusterId);

    /**
     * 根据集群ID逻辑删除
     * @param clusterIds
     * @return
     */
    int deleteBatch(Integer[] clusterIds);
}
