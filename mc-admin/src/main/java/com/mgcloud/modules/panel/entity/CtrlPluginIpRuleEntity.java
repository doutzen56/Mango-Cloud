package com.mgcloud.modules.panel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mgcloud.common.validator.group.AddGroup;
import com.mgcloud.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * IP限制
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@Data
@TableName("mc_ctrl_plugin_ip_rule")
public class CtrlPluginIpRuleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 站点ID
     */
    @NotBlank(message = "站点ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long siteId;
    /**
     * 白名单
     */
    private String whiteList;
    /**
     * 黑名单
     */
    private String blackList;

}
