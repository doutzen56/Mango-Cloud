package com.mgcloud.modules.customer.controller;

import java.util.Arrays;
import java.util.Map;

import com.mgcloud.common.validator.ValidatorUtils;
import com.mgcloud.modules.sys.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mgcloud.modules.customer.entity.ClustersEntity;
import com.mgcloud.modules.customer.service.ClustersService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;


/**
 * 集群列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-05 15:21:32
 */
@RestController
@RequestMapping("customer/clusters")
public class ClustersController {
    @Autowired
    private ClustersService clustersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("customer:clusters:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = clustersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("customer:clusters:info")
    public R info(@PathVariable("id") Integer id) {
        ClustersEntity clusters = clustersService.getById(id);

        return R.ok().put("clusters", clusters);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("customer:clusters:save")
    public R save(@RequestBody ClustersEntity clusters) {
        clusters.setCreatedBy(ShiroUtils.getUserName());
        clustersService.save(clusters);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("customer:clusters:update")
    public R update(@RequestBody ClustersEntity clusters) {
        ValidatorUtils.validateEntity(clusters);
        clustersService.updateById(clusters);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("customer:clusters:delete")
    public R delete(@RequestBody Integer[] ids) {
        clustersService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
