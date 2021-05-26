package com.mgcloud.modules.customer.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.mgcloud.common.annotation.SysLog;
import com.mgcloud.common.validator.ValidatorUtils;
import com.mgcloud.modules.customer.service.ClusterNodeService;
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
    @Autowired
    private ClusterNodeService clusterNodeService;
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
        //查询节点
        clusters.setNodeIdList(clusterNodeService.queryById(id));
        return R.ok().put("clusters", clusters);
    }

    /**
     * 保存
     */
    @SysLog("新增集群")
    @RequestMapping("/save")
    @RequiresPermissions("customer:clusters:save")
    public R save(@RequestBody ClustersEntity clusters) {

        ValidatorUtils.validateEntity(clusters);

        clustersService.saveCluster(clusters);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改集群")
    @RequestMapping("/update")
    @RequiresPermissions("customer:clusters:update")
    public R update(@RequestBody ClustersEntity clusters) {
        ValidatorUtils.validateEntity(clusters);
        clustersService.updateCluster(clusters);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除集群")
    @RequestMapping("/delete")
    @RequiresPermissions("customer:clusters:delete")
    public R delete(@RequestBody Integer[] ids) {
        clustersService.deleteBatch(ids);

        return R.ok();
    }
    @RequestMapping("/queryCulster")
    public R queryMap() {
        return R.ok("data", clustersService.list());
    }
}
