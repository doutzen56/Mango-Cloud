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

import com.mgcloud.modules.panel.entity.CtrlPluginLimitReqEntity;
import com.mgcloud.modules.panel.service.CtrlPluginLimitReqService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;


/**
 * 限制请求速度
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@RestController
@RequestMapping("panel/ctrlpluginlimitreq")
public class CtrlPluginLimitReqController {
    @Autowired
    private CtrlPluginLimitReqService ctrlPluginLimitReqService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("panel:ctrlpluginlimitreq:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = ctrlPluginLimitReqService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("panel:ctrlpluginlimitreq:info")
    public R info(@PathVariable("id") Long id) {
            CtrlPluginLimitReqEntity ctrlPluginLimitReq = ctrlPluginLimitReqService.getById(id);

        return R.ok().put("ctrlPluginLimitReq", ctrlPluginLimitReq);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("panel:ctrlpluginlimitreq:save")
    public R save(@RequestBody CtrlPluginLimitReqEntity ctrlPluginLimitReq) {
            ctrlPluginLimitReqService.save(ctrlPluginLimitReq);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("panel:ctrlpluginlimitreq:update")
    public R update(@RequestBody CtrlPluginLimitReqEntity ctrlPluginLimitReq) {
        ValidatorUtils.validateEntity(ctrlPluginLimitReq);
            ctrlPluginLimitReqService.updateById(ctrlPluginLimitReq);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("panel:ctrlpluginlimitreq:delete")
    public R delete(@RequestBody Long[] ids) {
            ctrlPluginLimitReqService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
