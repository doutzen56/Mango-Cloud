package com.mgcloud.modules.customer.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.mgcloud.common.validator.ValidatorUtils;
import com.mgcloud.modules.customer.service.ProvincialService;
import com.mgcloud.modules.sys.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mgcloud.modules.customer.entity.NodesEntity;
import com.mgcloud.modules.customer.service.NodesService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;


/**
 * 节点列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-05 15:21:32
 */
@RestController
@RequestMapping("customer/nodes")
public class NodesController {
    @Autowired
    private NodesService nodesService;
    @Autowired
    private ProvincialService provincialService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("customer:nodes:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = nodesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("customer:nodes:info")
    public R info(@PathVariable("id") Integer id) {
            NodesEntity nodes = nodesService.getById(id);

        return R.ok().put("nodes", nodes);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("customer:nodes:save")
    public R save(@RequestBody NodesEntity nodes) {
            nodes.setCreatedBy(ShiroUtils.getUserName());
            nodesService.save(nodes);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("customer:nodes:update")
    public R update(@RequestBody NodesEntity nodes) {
        ValidatorUtils.validateEntity(nodes);
            nodesService.updateById(nodes);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("customer:nodes:delete")
    public R delete(@RequestBody Integer[] ids) {
            nodesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    @RequestMapping("/queryMap/{status}")
    public R queryMap(@PathVariable(value = "status") Integer status) {
        Object result = nodesService.list()
                .stream()
                .filter(s -> status == -1 ? s.getStatus() == 0 || s.getStatus() == 1 : s.getStatus() == status)
                .collect(Collectors.toMap(k -> k.getId().toString(), v -> v.getName()));
        return R.ok("data", result);
    }
    @RequestMapping("/queryArea")
    public R queryArea(){
        return R.ok("data",provincialService.list());
    }
}
