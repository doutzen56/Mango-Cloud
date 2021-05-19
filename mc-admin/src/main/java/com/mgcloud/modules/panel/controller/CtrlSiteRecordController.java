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

import com.mgcloud.modules.panel.entity.CtrlSiteRecordEntity;
import com.mgcloud.modules.panel.service.CtrlSiteRecordService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;


/**
 * 站点记录
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@RestController
@RequestMapping("panel/ctrlsiterecord")
public class CtrlSiteRecordController {
    @Autowired
    private CtrlSiteRecordService ctrlSiteRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("panel:ctrlsiterecord:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = ctrlSiteRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("panel:ctrlsiterecord:info")
    public R info(@PathVariable("id") Long id) {
            CtrlSiteRecordEntity ctrlSiteRecord = ctrlSiteRecordService.getById(id);

        return R.ok().put("ctrlSiteRecord", ctrlSiteRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("panel:ctrlsiterecord:save")
    public R save(@RequestBody CtrlSiteRecordEntity ctrlSiteRecord) {
            ctrlSiteRecordService.save(ctrlSiteRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("panel:ctrlsiterecord:update")
    public R update(@RequestBody CtrlSiteRecordEntity ctrlSiteRecord) {
        ValidatorUtils.validateEntity(ctrlSiteRecord);
            ctrlSiteRecordService.updateById(ctrlSiteRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("panel:ctrlsiterecord:delete")
    public R delete(@RequestBody Long[] ids) {
            ctrlSiteRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
