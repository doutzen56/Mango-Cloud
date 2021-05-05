package com.mgcloud.modules.customer.dao;

import com.mgcloud.modules.customer.entity.CustomerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-05 15:21:32
 */
@Mapper
public interface CustomerDao extends BaseMapper<CustomerEntity> {

}
