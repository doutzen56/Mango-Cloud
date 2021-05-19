package com.mgcloud.modules.panel.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.panel.dao.CtrlSiteRealDao;
import com.mgcloud.modules.panel.entity.CtrlSiteRealEntity;
import com.mgcloud.modules.panel.service.CtrlSiteRealService;


@Service("ctrlSiteRealService")
public class CtrlSiteRealServiceImpl extends ServiceImpl<CtrlSiteRealDao, CtrlSiteRealEntity> implements CtrlSiteRealService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CtrlSiteRealEntity> page = this.page(
                new Query<CtrlSiteRealEntity>().getPage(params),
                new QueryWrapper<CtrlSiteRealEntity>()
        );

        return new PageUtils(page);
    }

}
