package com.mgcloud.modules.panel.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.panel.dao.CtrlSiteRecordDao;
import com.mgcloud.modules.panel.entity.CtrlSiteRecordEntity;
import com.mgcloud.modules.panel.service.CtrlSiteRecordService;


@Service("ctrlSiteRecordService")
public class CtrlSiteRecordServiceImpl extends ServiceImpl<CtrlSiteRecordDao, CtrlSiteRecordEntity> implements CtrlSiteRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CtrlSiteRecordEntity> page = this.page(
                new Query<CtrlSiteRecordEntity>().getPage(params),
                new QueryWrapper<CtrlSiteRecordEntity>()
        );

        return new PageUtils(page);
    }

}
