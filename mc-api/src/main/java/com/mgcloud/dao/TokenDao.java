

package com.mgcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgcloud.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Token
 * <p>
 * tzen@e-veb.com
 */
@Mapper
public interface TokenDao extends BaseMapper<TokenEntity> {

}
