package com.mgcloud.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-04 15:45:23
 */
@Data
@TableName("mc_per_admin")
public class PerAdminEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机
     */
    private String mobile;
    /**
     * Skype
     */
    private String skype;
    /**
     * email
     */
    private String email;
    /**
     * 语言
     */
    private String language;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
    /**
     * 最近登录
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLogin;
    /**
     * 关联客户
     */
    private Integer customerId;
    /**
     * 状态
     */
    private Integer status;

}
