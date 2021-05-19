package com.mgcloud.modules.panel.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.panel.dao.CtrlPluginLimitReqDao;
import com.mgcloud.modules.panel.entity.CtrlPluginLimitReqEntity;
import com.mgcloud.modules.panel.service.CtrlPluginLimitReqService;


@Service("ctrlPluginLimitReqService")
public class CtrlPluginLimitReqServiceImpl extends ServiceImpl<CtrlPluginLimitReqDao, CtrlPluginLimitReqEntity> implements CtrlPluginLimitReqService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CtrlPluginLimitReqEntity> page = this.page(
                new Query<CtrlPluginLimitReqEntity>().getPage(params),
                new QueryWrapper<CtrlPluginLimitReqEntity>()
        );

        return new PageUtils(page);
    }

}
