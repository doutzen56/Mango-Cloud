package com.mgcloud.modules.customer.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.customer.dao.NodesDao;
import com.mgcloud.modules.customer.entity.NodesEntity;
import com.mgcloud.modules.customer.service.NodesService;


@Service("nodesService")
public class NodesServiceImpl extends ServiceImpl<NodesDao, NodesEntity> implements NodesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NodesEntity> page = this.page(
                new Query<NodesEntity>().getPage(params),
                new QueryWrapper<NodesEntity>()
        );

        return new PageUtils(page);
    }

}
