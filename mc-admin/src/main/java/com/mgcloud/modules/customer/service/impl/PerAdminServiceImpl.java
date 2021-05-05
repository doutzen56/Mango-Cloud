package com.mgcloud.modules.customer.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.customer.dao.PerAdminDao;
import com.mgcloud.modules.customer.entity.PerAdminEntity;
import com.mgcloud.modules.customer.service.PerAdminService;


@Service("perAdminService")
public class PerAdminServiceImpl extends ServiceImpl<PerAdminDao, PerAdminEntity> implements PerAdminService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PerAdminEntity> page = this.page(
                new Query<PerAdminEntity>().getPage(params),
                new QueryWrapper<PerAdminEntity>()
        );

        return new PageUtils(page);
    }

}
