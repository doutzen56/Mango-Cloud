

package com.mgcloud.modules.sys.controller;

import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;
import com.mgcloud.common.validator.ValidatorUtils;
import com.mgcloud.modules.sys.entity.SysDictEntity;
import com.mgcloud.modules.sys.service.SysDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据字典
 * <p>
 * tzen@e-veb.com
 */
@RestController
@RequestMapping("sys/dict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dict:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDictService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public R info(@PathVariable("id") Long id) {
        SysDictEntity dict = sysDictService.getById(id);

        return R.ok().put("dict", dict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dict:save")
    public R save(@RequestBody SysDictEntity dict) {
        //校验类型
        ValidatorUtils.validateEntity(dict);

        sysDictService.save(dict);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dict:update")
    public R update(@RequestBody SysDictEntity dict) {
        //校验类型
        ValidatorUtils.validateEntity(dict);

        sysDictService.updateById(dict);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    public R delete(@RequestBody Long[] ids) {
        sysDictService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 根据字典类型查询字典列表
     *
     * @param type 字典类型
     * @return type对应字典值
     */
    @RequestMapping("/queryDictByType/{type}")
    public R queryDictByType(@PathVariable("type") String type) {
        Object result = sysDictService.list()
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
        return R.ok("data", result);
    }
}
