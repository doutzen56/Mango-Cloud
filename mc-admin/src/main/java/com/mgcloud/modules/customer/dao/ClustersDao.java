package com.mgcloud.modules.customer.dao;

import com.mgcloud.modules.customer.entity.ClustersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 集群列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-05 15:21:32
 */
@Mapper
public interface ClustersDao extends BaseMapper<ClustersEntity> {

}
