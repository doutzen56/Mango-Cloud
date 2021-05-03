

package com.mgcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgcloud.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * <p>
 * tzen@e-veb.com
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
