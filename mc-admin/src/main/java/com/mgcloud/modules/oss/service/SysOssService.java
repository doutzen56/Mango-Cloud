

package com.mgcloud.modules.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.modules.oss.entity.SysOssEntity;
import com.mgcloud.common.utils.PageUtils;

import java.util.Map;

/**
 * 文件上传
 * <p>
 * tzen@e-veb.com
 */
public interface SysOssService extends IService<SysOssEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
