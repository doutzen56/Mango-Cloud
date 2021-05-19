package com.mgcloud.modules.panel.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.panel.dao.CtrlPluginIpRuleDao;
import com.mgcloud.modules.panel.entity.CtrlPluginIpRuleEntity;
import com.mgcloud.modules.panel.service.CtrlPluginIpRuleService;


@Service("ctrlPluginIpRuleService")
public class CtrlPluginIpRuleServiceImpl extends ServiceImpl<CtrlPluginIpRuleDao, CtrlPluginIpRuleEntity> implements CtrlPluginIpRuleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CtrlPluginIpRuleEntity> page = this.page(
                new Query<CtrlPluginIpRuleEntity>().getPage(params),
                new QueryWrapper<CtrlPluginIpRuleEntity>()
        );

        return new PageUtils(page);
    }

}
