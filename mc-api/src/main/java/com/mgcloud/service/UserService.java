

package com.mgcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgcloud.entity.UserEntity;
import com.mgcloud.form.LoginForm;

import java.util.Map;

/**
 * 用户
 * <p>
 * tzen@e-veb.com
 */
public interface UserService extends IService<UserEntity> {

    UserEntity queryByMobile(String mobile);

    /**
     * 用户登录
     *
     * @param form 登录表单
     * @return 返回登录信息
     */
    Map<String, Object> login(LoginForm form);
}
