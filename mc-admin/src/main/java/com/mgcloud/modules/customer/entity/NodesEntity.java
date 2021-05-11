package com.mgcloud.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 节点列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-05 15:21:32
 */
@Data
@TableName("mc_nodes")
public class NodesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 公网IP
     */
    private String publicIp;
    /**
     * 内网IP
     */
    private String privateIp;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 记录
     */
    private String record;
    /**
     * 总带宽
     */
    private Integer bandwidthTotal;
    /**
     * 带宽临界点
     */
    private Integer bandwidthThreshold;
    /**
     * 当前带宽
     */
    private Integer bandwidthCurrent;
    /**
     * 连接数
     */
    private Integer connection;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 所属区域ID
     */
    private Integer areaId;
    /**
     * 所属区域
     */
    //private String areaName;
    /**
     * 供应商
     */
    private Integer providerType;

}
