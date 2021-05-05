package com.mgcloud.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

    import java.io.Serializable;
import java.util.Date;

/**
 * 份列表
 *
 * @author tzen
 * @email tzen@mango.cloud.com
 * @date 2021-05-05 15:21:32
 */
@Data
@TableName("mc_provincial")
public class ProvincialEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */
                @TableId
            private Integer id;
            /**
         * 份
         */
            private String name;
    
}
