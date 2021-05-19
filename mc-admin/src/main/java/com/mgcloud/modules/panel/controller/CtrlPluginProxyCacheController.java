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

import com.mgcloud.modules.panel.entity.CtrlPluginProxyCacheEntity;
import com.mgcloud.modules.panel.service.CtrlPluginProxyCacheService;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;


/**
 * 代理缓存
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@RestController
@RequestMapping("panel/ctrlpluginproxycache")
public class CtrlPluginProxyCacheController {
    @Autowired
    private CtrlPluginProxyCacheService ctrlPluginProxyCacheService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("panel:ctrlpluginproxycache:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = ctrlPluginProxyCacheService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("panel:ctrlpluginproxycache:info")
    public R info(@PathVariable("id") Long id) {
            CtrlPluginProxyCacheEntity ctrlPluginProxyCache = ctrlPluginProxyCacheService.getById(id);

        return R.ok().put("ctrlPluginProxyCache", ctrlPluginProxyCache);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("panel:ctrlpluginproxycache:save")
    public R save(@RequestBody CtrlPluginProxyCacheEntity ctrlPluginProxyCache) {
            ctrlPluginProxyCacheService.save(ctrlPluginProxyCache);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("panel:ctrlpluginproxycache:update")
    public R update(@RequestBody CtrlPluginProxyCacheEntity ctrlPluginProxyCache) {
        ValidatorUtils.validateEntity(ctrlPluginProxyCache);
            ctrlPluginProxyCacheService.updateById(ctrlPluginProxyCache);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("panel:ctrlpluginproxycache:delete")
    public R delete(@RequestBody Long[] ids) {
            ctrlPluginProxyCacheService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
