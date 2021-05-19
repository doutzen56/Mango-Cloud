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
 * 限制请求速度
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@Data
@TableName("mc_ctrl_plugin_limit_req")
public class CtrlPluginLimitReqEntity implements Serializable {
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
     * 指定的请求速率（以秒为单位），请求速率超过 rate 但没有超过 （rate + brust）的请求会被加上延时。
     */
    private Integer rate;
    /**
     * 请求速率超过 （rate + brust）的请求会被直接拒绝。
     */
    private Integer burst;
    /**
     * 是用来做请求计数的依据，默认的key为：”remote_addr”(客户端IP地址)
     */
    private String key;
    /**
     * 当请求超过阈值被拒绝时，返回的 HTTP 状态码，默认 503。
     */
    private Integer rejectedCode;

}
