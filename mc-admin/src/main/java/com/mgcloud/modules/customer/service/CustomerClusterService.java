package com.mgcloud.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.customer.entity.CustomerClusterEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-06 10:38:24
 */
public interface CustomerClusterService extends IService<CustomerClusterEntity> {

    PageUtils queryPage(Map<String, Object> params);
    /**
     * 根据客户ID查询所属节点ID
     * @param customerId 客户ID
     * @return
     */
    List<Integer> queryById(Integer customerId);

    /**
     * 根据客户ID逻辑删除
     * @param customerIds 客户id集合
     * @return
     */
    int deleteBatch(Integer[] customerIds);

    /**
     * 保存客户与集群之间的关系
     * @param customerId 客户ID
     * @param clusterIdList 集群ID集合
     */
    void saveOrUpdate(Integer customerId,List<Integer> clusterIdList);
}

