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
 * 代理缓存
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@Data
@TableName("mc_ctrl_plugin_proxy_cache")
public class CtrlPluginProxyCacheEntity implements Serializable {
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
     * 是否开启缓存
     */
    private Integer cacheState;
    /**
     * 缓存区域
     */
    private String cacheZone;
    /**
     * 缓存key
     */
    private String cacheKey;
    /**
     * http方法
     */
    private String cacheMethod;
    /**
     * http状态码
     */
    private String cacheHttpStatus;
    /**
     * 浏览器缓存时间
     */
    private Integer cacheBrowser;
    /**
     * 边缘节点缓存时间
     */
    private Integer cacheEdge;

}
