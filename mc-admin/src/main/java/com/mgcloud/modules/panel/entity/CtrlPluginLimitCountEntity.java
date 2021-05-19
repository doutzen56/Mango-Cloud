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
 * 访问速率限制
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@Data
@TableName("mc_ctrl_plugin_limit_count")
public class CtrlPluginLimitCountEntity implements Serializable {
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
     * 指定时间窗口内的请求数量阈值
     */
    private Integer count;
    /**
     * 时间窗口的大小（以秒为单位），超过这个时间就会重置
     */
    private Integer timeWindos;
    /**
     * 当请求超过阈值被拒绝时，返回的 HTTP 状态码，默认 503
     */
    private Integer rejectedCode;

}
