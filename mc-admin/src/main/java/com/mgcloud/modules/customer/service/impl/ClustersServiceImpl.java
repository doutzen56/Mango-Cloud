package com.mgcloud.modules.customer.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.customer.dao.ClustersDao;
import com.mgcloud.modules.customer.entity.ClustersEntity;
import com.mgcloud.modules.customer.service.ClustersService;


@Service("clustersService")
public class ClustersServiceImpl extends ServiceImpl<ClustersDao, ClustersEntity> implements ClustersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ClustersEntity> page = this.page(
                new Query<ClustersEntity>().getPage(params),
                new QueryWrapper<ClustersEntity>()
        );

        return new PageUtils(page);
    }

}
