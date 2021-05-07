package com.mgcloud.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-06 10:38:24
 */
@Data
@TableName("mc_cluster_node")
public class ClusterNodeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *
     */
    private Integer clusterId;
    /**
     *
     */
    private Integer nodeId;

}
