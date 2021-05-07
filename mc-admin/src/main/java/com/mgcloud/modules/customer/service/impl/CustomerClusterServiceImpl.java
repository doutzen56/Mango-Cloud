package com.mgcloud.modules.customer.service.impl;

import com.mgcloud.modules.customer.entity.ClusterNodeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.customer.dao.CustomerClusterDao;
import com.mgcloud.modules.customer.entity.CustomerClusterEntity;
import com.mgcloud.modules.customer.service.CustomerClusterService;
import org.springframework.transaction.annotation.Transactional;


@Service("customerClusterService")
public class CustomerClusterServiceImpl extends ServiceImpl<CustomerClusterDao, CustomerClusterEntity> implements CustomerClusterService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerClusterEntity> page = this.page(
                new Query<CustomerClusterEntity>().getPage(params),
                new QueryWrapper<CustomerClusterEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据客户ID查询所属节点ID
     *
     * @param customerId 客户ID
     * @return
     */
    @Override
    public List<Integer> queryById(Integer customerId) {
        return baseMapper.queryById(customerId);
    }

    /**
     * 根据客户ID逻辑删除
     *
     * @param customerIds 客户id集合
     * @return
     */
    @Override
    public int deleteBatch(Integer[] customerIds) {
        return baseMapper.deleteBatch(customerIds);
    }

    /**
     * 保存客户与集群之间的关系
     *
     * @param customerId    客户ID
     * @param clusterIdList 集群ID集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Integer customerId, List<Integer> clusterIdList) {
        //先删除客户与集群关系
        this.deleteBatch(new Integer[]{customerId});
        if (clusterIdList.size() == 0) {
            return;
        }
        //保存新的集群关系
        for (Integer clusterId : clusterIdList) {
            CustomerClusterEntity entity = new CustomerClusterEntity();
            entity.setClusterId(clusterId);
            entity.setCustomerId(customerId);
            this.save(entity);
        }
    }

}
