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

import com.mgcloud.modules.panel.entity.CtrlPluginIpRuleEntity;
import com.mgcloud.modules.panel.service.CtrlPluginIpRuleService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;


/**
 * IP限制
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@RestController
@RequestMapping("panel/ctrlpluginiprule")
public class CtrlPluginIpRuleController {
    @Autowired
    private CtrlPluginIpRuleService ctrlPluginIpRuleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("panel:ctrlpluginiprule:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = ctrlPluginIpRuleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("panel:ctrlpluginiprule:info")
    public R info(@PathVariable("id") Long id) {
            CtrlPluginIpRuleEntity ctrlPluginIpRule = ctrlPluginIpRuleService.getById(id);

        return R.ok().put("ctrlPluginIpRule", ctrlPluginIpRule);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("panel:ctrlpluginiprule:save")
    public R save(@RequestBody CtrlPluginIpRuleEntity ctrlPluginIpRule) {
            ctrlPluginIpRuleService.save(ctrlPluginIpRule);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("panel:ctrlpluginiprule:update")
    public R update(@RequestBody CtrlPluginIpRuleEntity ctrlPluginIpRule) {
        ValidatorUtils.validateEntity(ctrlPluginIpRule);
            ctrlPluginIpRuleService.updateById(ctrlPluginIpRule);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("panel:ctrlpluginiprule:delete")
    public R delete(@RequestBody Long[] ids) {
            ctrlPluginIpRuleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
