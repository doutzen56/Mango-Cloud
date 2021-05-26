package com.mgcloud.modules.customer.service.impl;

import com.mgcloud.common.utils.DateUtils;
import com.mgcloud.modules.customer.dao.NodesDao;
import com.mgcloud.modules.customer.service.ClusterNodeService;
import com.mgcloud.modules.sys.shiro.ShiroUtils;
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

import com.mgcloud.modules.customer.dao.ClustersDao;
import com.mgcloud.modules.customer.entity.ClustersEntity;
import com.mgcloud.modules.customer.service.ClustersService;
import org.springframework.transaction.annotation.Transactional;


@Service("clustersService")
public class ClustersServiceImpl extends ServiceImpl<ClustersDao, ClustersEntity> implements ClustersService {
    @Autowired
    private ClusterNodeService clusterNodeService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ClustersEntity> page = this.page(
                new Query<ClustersEntity>().getPage(params),
                new QueryWrapper<ClustersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCluster(ClustersEntity entity) {
        entity.setCreatedTime(DateUtils.LOCAL_DATETIME);
        entity.setCreatedBy(ShiroUtils.getUserName());
        this.save(entity);
        //保存集群与节点之前的关系
        clusterNodeService.saveOrUpdate(entity.getId(), entity.getNodeIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCluster(ClustersEntity entity) {
        this.updateById(entity);
        //保存集群与节点之前的关系
        clusterNodeService.saveOrUpdate(entity.getId(), entity.getNodeIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] ids) {
        //删除集群
        this.removeByIds(Arrays.asList(ids));
        //删除节点关系
        clusterNodeService.deleteBatch(ids);
    }

}
