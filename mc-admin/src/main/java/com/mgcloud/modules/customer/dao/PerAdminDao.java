package com.mgcloud.modules.customer.dao;

import com.mgcloud.modules.customer.entity.PerAdminEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-04 15:45:23
 */
@Mapper
public interface PerAdminDao extends BaseMapper<PerAdminEntity> {

}
