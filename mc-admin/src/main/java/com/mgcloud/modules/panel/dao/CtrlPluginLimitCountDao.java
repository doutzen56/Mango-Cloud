package com.mgcloud.modules.panel.dao;

import com.mgcloud.modules.panel.entity.CtrlPluginLimitCountEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 访问速率限制
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@Mapper
public interface CtrlPluginLimitCountDao extends BaseMapper<CtrlPluginLimitCountEntity> {

}
