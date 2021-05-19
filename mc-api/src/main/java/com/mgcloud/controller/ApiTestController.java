

package com.mgcloud.controller;

import com.mgcloud.annotation.Login;
import com.mgcloud.common.utils.R;
import com.mgcloud.annotation.LoginUser;
import com.mgcloud.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 测试接口
 * <p>
 * tzen@e-veb.com
 */
@RestController
@RequestMapping("/api")
@Api(tags = "测试接口")
public class ApiTestController {

    @Login
    @GetMapping("userInfo")
    @ApiOperation(value = "获取用户信息", response = UserEntity.class)
    public R userInfo(@ApiIgnore @LoginUser UserEntity user) {
        return R.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public R userInfo(@ApiIgnore @RequestAttribute("userId") Integer userId) {
        return R.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public R notToken() {
        return R.ok().put("msg", "无需token也能访问。。。");
    }

    @GetMapping("testJson")
    @ApiOperation("忽略Token验证测试")
    public R testJson() {

        return R.ok();
    }

}
