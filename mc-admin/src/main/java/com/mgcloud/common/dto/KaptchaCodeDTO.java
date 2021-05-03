package com.mgcloud.common.dto;

import lombok.Data;

/**
 * Created by tzen
 * tzen@e-veb.com
 * 2021/4/17 15:41
 */
@Data
public class KaptchaCodeDTO {
    private String codeKey;
    private String codeValue;
}
