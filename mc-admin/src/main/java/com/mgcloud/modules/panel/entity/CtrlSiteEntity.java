package com.mgcloud.modules.panel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mgcloud.common.validator.group.AddGroup;
import com.mgcloud.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 站点列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:58
 */
@Data
@TableName("mc_ctrl_site")
public class CtrlSiteEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 站点名称
     */
    @NotBlank(message = "站点名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    /**
     * 站点类型
     */
    @NotBlank(message = "站点类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer type;
    /**
     * 是否启用
     */
    private Integer status;
    /**
     * 重试次数
     */
    private Integer retries;
    /**
     * 是否开启websocket
     */
    private Integer enableWebsocket;
    /**
     * 预留字段
     */
    private String key;
    /**
     * 连接数
     */
    private Integer timeoutConnect;
    /**
     * 发送数
     */
    private Integer timeoutSend;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 读取数
     */
    private Integer timeoutRead;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

}
