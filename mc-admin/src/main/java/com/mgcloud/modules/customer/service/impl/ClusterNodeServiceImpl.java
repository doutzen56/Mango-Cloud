package com.mgcloud.modules.customer.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgcloud.common.utils.PageUtils;
import com.mgcloud.common.utils.Query;

import com.mgcloud.modules.customer.dao.ClusterNodeDao;
import com.mgcloud.modules.customer.entity.ClusterNodeEntity;
import com.mgcloud.modules.customer.service.ClusterNodeService;
import org.springframework.transaction.annotation.Transactional;


@Service("clusterNodeService")
public class ClusterNodeServiceImpl extends ServiceImpl<ClusterNodeDao, ClusterNodeEntity> implements ClusterNodeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ClusterNodeEntity> page = this.page(
                new Query<ClusterNodeEntity>().getPage(params),
                new QueryWrapper<ClusterNodeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Integer> queryById(Integer id) {
        return baseMapper.queryById(id);
    }

    @Override
    public int deleteBatch(Integer[] clusterIds) {
        return baseMapper.deleteBatch(clusterIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Integer clusterId, List<Integer> nodeIdList) {
        //先删除集群关系
        this.deleteBatch(new Integer[]{clusterId});
        if (nodeIdList.size() == 0) {
            return;
        }
        //保存新的集群关系
        for (Integer nodeId : nodeIdList) {
            ClusterNodeEntity entity = new ClusterNodeEntity();
            entity.setClusterId(clusterId);
            entity.setNodeId(nodeId);
            this.save(entity);
        }
    }
}
