package com.mgcloud.modules.panel.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.panel.dao.CtrlPluginProxyCacheDao;
import com.mgcloud.modules.panel.entity.CtrlPluginProxyCacheEntity;
import com.mgcloud.modules.panel.service.CtrlPluginProxyCacheService;


@Service("ctrlPluginProxyCacheService")
public class CtrlPluginProxyCacheServiceImpl extends ServiceImpl<CtrlPluginProxyCacheDao, CtrlPluginProxyCacheEntity> implements CtrlPluginProxyCacheService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CtrlPluginProxyCacheEntity> page = this.page(
                new Query<CtrlPluginProxyCacheEntity>().getPage(params),
                new QueryWrapper<CtrlPluginProxyCacheEntity>()
        );

        return new PageUtils(page);
    }

}
