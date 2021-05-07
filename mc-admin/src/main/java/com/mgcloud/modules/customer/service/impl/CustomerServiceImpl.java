package com.mgcloud.modules.customer.service.impl;

import com.mgcloud.common.utils.DateUtils;
import com.mgcloud.modules.customer.service.ClusterNodeService;
import com.mgcloud.modules.customer.service.CustomerClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.customer.dao.CustomerDao;
import com.mgcloud.modules.customer.entity.CustomerEntity;
import com.mgcloud.modules.customer.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;


@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {
    @Autowired
    private CustomerClusterService  customerClusterService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerEntity> page = this.page(
                new Query<CustomerEntity>().getPage(params),
                new QueryWrapper<CustomerEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCustomer(CustomerEntity entity) {
        entity.setCreatedTime(DateUtils.LOCAL_DATETIME);
        entity.setUpdatedTime(DateUtils.LOCAL_DATETIME);
        this.save(entity);
        //保存客户与集群之前的关系
        customerClusterService.saveOrUpdate(entity.getId(), entity.getClusterIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomer(CustomerEntity entity) {
        entity.setUpdatedTime(DateUtils.LOCAL_DATETIME);
        this.updateById(entity);
        //保存客户与集群之前的关系
        customerClusterService.saveOrUpdate(entity.getId(), entity.getClusterIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] ids) {
        //删除客户
        this.removeByIds(Arrays.asList(ids));
        //删除集群关系
        customerClusterService.deleteBatch(ids);
    }

}
