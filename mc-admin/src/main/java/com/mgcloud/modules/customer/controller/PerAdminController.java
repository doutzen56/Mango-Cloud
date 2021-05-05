package com.mgcloud.modules.customer.controller;

import java.util.Arrays;
import java.util.Map;

import com.mgcloud.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mgcloud.modules.customer.entity.PerAdminEntity;
import com.mgcloud.modules.customer.service.PerAdminService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;


/**
 * 账户列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-04 15:45:23
 */
@RestController
@RequestMapping("customer/peradmin")
public class PerAdminController {
    @Autowired
    private PerAdminService perAdminService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("customer:peradmin:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = perAdminService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("customer:peradmin:info")
    public R info(@PathVariable("id") Integer id) {
        PerAdminEntity perAdmin = perAdminService.getById(id);

        return R.ok().put("perAdmin", perAdmin);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("customer:peradmin:save")
    public R save(@RequestBody PerAdminEntity perAdmin) {
        perAdminService.save(perAdmin);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("customer:peradmin:update")
    public R update(@RequestBody PerAdminEntity perAdmin) {
        ValidatorUtils.validateEntity(perAdmin);
        perAdminService.updateById(perAdmin);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("customer:peradmin:delete")
    public R delete(@RequestBody Integer[] ids) {
        perAdminService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
