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

import com.mgcloud.modules.panel.entity.CtrlSiteRealEntity;
import com.mgcloud.modules.panel.service.CtrlSiteRealService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;


/**
 * 源点列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@RestController
@RequestMapping("panel/ctrlsitereal")
public class CtrlSiteRealController {
    @Autowired
    private CtrlSiteRealService ctrlSiteRealService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("panel:ctrlsitereal:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = ctrlSiteRealService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("panel:ctrlsitereal:info")
    public R info(@PathVariable("id") Long id) {
            CtrlSiteRealEntity ctrlSiteReal = ctrlSiteRealService.getById(id);

        return R.ok().put("ctrlSiteReal", ctrlSiteReal);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("panel:ctrlsitereal:save")
    public R save(@RequestBody CtrlSiteRealEntity ctrlSiteReal) {
            ctrlSiteRealService.save(ctrlSiteReal);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("panel:ctrlsitereal:update")
    public R update(@RequestBody CtrlSiteRealEntity ctrlSiteReal) {
        ValidatorUtils.validateEntity(ctrlSiteReal);
            ctrlSiteRealService.updateById(ctrlSiteReal);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("panel:ctrlsitereal:delete")
    public R delete(@RequestBody Long[] ids) {
            ctrlSiteRealService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
