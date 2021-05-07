package com.mgcloud.modules.customer.dao;

import com.mgcloud.modules.customer.entity.CustomerClusterEntity;
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
public interface CustomerClusterDao extends BaseMapper<CustomerClusterEntity> {
    /**
     * 根据客户ID查询所属集群ID
     * @param customerId 客户ID
     * @return
     */
    List<Integer> queryById(Integer customerId);

    /**
     * 根据客户ID逻辑删除
     * @param customerIds
     * @return
     */
    int deleteBatch(Integer[] customerIds);
}
