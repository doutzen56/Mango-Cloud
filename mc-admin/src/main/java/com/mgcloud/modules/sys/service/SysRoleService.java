

package com.mgcloud.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.modules.sys.entity.SysRoleEntity;

import java.util.Map;


/**
 * 角色
 * <p>
 * tzen@e-veb.com
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveRole(SysRoleEntity role);

    void update(SysRoleEntity role);

    void deleteBatch(Long[] roleIds);

}
