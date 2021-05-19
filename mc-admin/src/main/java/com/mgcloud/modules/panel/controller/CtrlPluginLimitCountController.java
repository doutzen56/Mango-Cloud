package com.mgcloud.modules.panel.controller;

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

import com.mgcloud.modules.panel.entity.CtrlPluginLimitCountEntity;
import com.mgcloud.modules.panel.service.CtrlPluginLimitCountService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;


/**
 * 访问速率限制
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@RestController
@RequestMapping("panel/ctrlpluginlimitcount")
public class CtrlPluginLimitCountController {
    @Autowired
    private CtrlPluginLimitCountService ctrlPluginLimitCountService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("panel:ctrlpluginlimitcount:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = ctrlPluginLimitCountService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("panel:ctrlpluginlimitcount:info")
    public R info(@PathVariable("id") Long id) {
            CtrlPluginLimitCountEntity ctrlPluginLimitCount = ctrlPluginLimitCountService.getById(id);

        return R.ok().put("ctrlPluginLimitCount", ctrlPluginLimitCount);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("panel:ctrlpluginlimitcount:save")
    public R save(@RequestBody CtrlPluginLimitCountEntity ctrlPluginLimitCount) {
            ctrlPluginLimitCountService.save(ctrlPluginLimitCount);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("panel:ctrlpluginlimitcount:update")
    public R update(@RequestBody CtrlPluginLimitCountEntity ctrlPluginLimitCount) {
        ValidatorUtils.validateEntity(ctrlPluginLimitCount);
            ctrlPluginLimitCountService.updateById(ctrlPluginLimitCount);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("panel:ctrlpluginlimitcount:delete")
    public R delete(@RequestBody Long[] ids) {
            ctrlPluginLimitCountService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
