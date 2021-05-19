package com.mgcloud.modules.panel.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.panel.dao.CtrlPluginLimitCountDao;
import com.mgcloud.modules.panel.entity.CtrlPluginLimitCountEntity;
import com.mgcloud.modules.panel.service.CtrlPluginLimitCountService;


@Service("ctrlPluginLimitCountService")
public class CtrlPluginLimitCountServiceImpl extends ServiceImpl<CtrlPluginLimitCountDao, CtrlPluginLimitCountEntity> implements CtrlPluginLimitCountService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CtrlPluginLimitCountEntity> page = this.page(
                new Query<CtrlPluginLimitCountEntity>().getPage(params),
                new QueryWrapper<CtrlPluginLimitCountEntity>()
        );

        return new PageUtils(page);
    }

}
