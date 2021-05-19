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

import com.mgcloud.modules.panel.entity.CtrlSiteEntity;
import com.mgcloud.modules.panel.service.CtrlSiteService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;


/**
 * 站点列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:58
 */
@RestController
@RequestMapping("panel/ctrlsite")
public class CtrlSiteController {
    @Autowired
    private CtrlSiteService ctrlSiteService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("panel:ctrlsite:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = ctrlSiteService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("panel:ctrlsite:info")
    public R info(@PathVariable("id") Long id) {
            CtrlSiteEntity ctrlSite = ctrlSiteService.getById(id);

        return R.ok().put("ctrlSite", ctrlSite);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("panel:ctrlsite:save")
    public R save(@RequestBody CtrlSiteEntity ctrlSite) {
            ctrlSiteService.save(ctrlSite);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("panel:ctrlsite:update")
    public R update(@RequestBody CtrlSiteEntity ctrlSite) {
        ValidatorUtils.validateEntity(ctrlSite);
            ctrlSiteService.updateById(ctrlSite);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("panel:ctrlsite:delete")
    public R delete(@RequestBody Long[] ids) {
            ctrlSiteService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
