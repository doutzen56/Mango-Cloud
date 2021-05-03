

package com.mgcloud.modules.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgcloud.modules.job.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 定时任务
 * <p>
 * tzen@e-veb.com
 */
@Mapper
public interface ScheduleJobDao extends BaseMapper<ScheduleJobEntity> {

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}
