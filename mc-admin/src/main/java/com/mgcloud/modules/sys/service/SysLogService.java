

package com.mgcloud.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 * <p>
 * tzen@e-veb.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
