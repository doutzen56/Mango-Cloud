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
 * 站点记录
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-19 10:56:59
 */
@Data
@TableName("mc_ctrl_site_record")
public class CtrlSiteRecordEntity implements Serializable {
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
     * 主机记录值
     */
    private String hostRecord;
    /**
     * 顶级域名
     */
    private String domain;
    /**
     * 是否启用SSL
     */
    private Integer enableSsl;

}
