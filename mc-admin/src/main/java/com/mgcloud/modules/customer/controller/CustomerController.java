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

import com.mgcloud.modules.customer.entity.CustomerEntity;
import com.mgcloud.modules.customer.service.CustomerService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;


/**
 * 客户列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-05 15:21:32
 */
@RestController
@RequestMapping("customer/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("customer:customer:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = customerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("customer:customer:info")
    public R info(@PathVariable("id") Integer id) {
        CustomerEntity customer = customerService.getById(id);

        return R.ok().put("customer", customer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("customer:customer:save")
    public R save(@RequestBody CustomerEntity customer) {
        customerService.save(customer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("customer:customer:update")
    public R update(@RequestBody CustomerEntity customer) {
        ValidatorUtils.validateEntity(customer);
        customerService.updateById(customer);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("customer:customer:delete")
    public R delete(@RequestBody Integer[] ids) {
        customerService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
