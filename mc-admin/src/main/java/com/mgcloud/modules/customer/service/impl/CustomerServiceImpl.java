package com.mgcloud.modules.customer.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.customer.dao.CustomerDao;
import com.mgcloud.modules.customer.entity.CustomerEntity;
import com.mgcloud.modules.customer.service.CustomerService;


@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerEntity> page = this.page(
                new Query<CustomerEntity>().getPage(params),
                new QueryWrapper<CustomerEntity>()
        );

        return new PageUtils(page);
    }

}
