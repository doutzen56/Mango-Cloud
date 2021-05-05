package com.mgcloud.modules.customer.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.customer.dao.ProvincialDao;
import com.mgcloud.modules.customer.entity.ProvincialEntity;
import com.mgcloud.modules.customer.service.ProvincialService;


@Service("provincialService")
public class ProvincialServiceImpl extends ServiceImpl<ProvincialDao, ProvincialEntity> implements ProvincialService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProvincialEntity> page = this.page(
                new Query<ProvincialEntity>().getPage(params),
                new QueryWrapper<ProvincialEntity>()
        );

        return new PageUtils(page);
    }

}
