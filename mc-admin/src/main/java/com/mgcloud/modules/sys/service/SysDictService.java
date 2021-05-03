

package com.mgcloud.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.sys.entity.SysDictEntity;

import java.util.Map;

/**
 * 数据字典
 * <p>
 * tzen@e-veb.com
 */
public interface SysDictService extends IService<SysDictEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

