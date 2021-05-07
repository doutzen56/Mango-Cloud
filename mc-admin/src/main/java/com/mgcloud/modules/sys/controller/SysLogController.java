

package com.mgcloud.modules.sys.controller;

import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.R;
import com.mgcloud.modules.sys.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 系统日志
 * <p>
 * tzen@e-veb.com
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys:log:list")
    public R list(@RequestParam Map<String, Object> params) {
        if(params.get("sidx").equals("")){
            params.put("sidx","id");
            params.put("order","desc");
        }
        PageUtils page = sysLogService.queryPage(params);

        return R.ok().put("page", page);
    }

}
