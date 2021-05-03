

package com.mgcloud.common.validator;

import com.mgcloud.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * <p>
 * tzen@e-veb.com
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
