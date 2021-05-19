package com.mgcloud.modules.panel.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.panel.dao.CtrlSiteDao;
import com.mgcloud.modules.panel.entity.CtrlSiteEntity;
import com.mgcloud.modules.panel.service.CtrlSiteService;


@Service("ctrlSiteService")
public class CtrlSiteServiceImpl extends ServiceImpl<CtrlSiteDao, CtrlSiteEntity> implements CtrlSiteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CtrlSiteEntity> page = this.page(
                new Query<CtrlSiteEntity>().getPage(params),
                new QueryWrapper<CtrlSiteEntity>()
        );

        return new PageUtils(page);
    }

}
