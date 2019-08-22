package com.zark.sbproject.boot.service.user.util;

import com.zark.sbproject.boot.api.user.bo.UserBO;
import com.zark.sbproject.boot.dao.user.entity.UserPO;
import org.springframework.beans.BeanUtils;

/**
 * @author zark
 * @date 2019-08-16
 */
public class UserUtil {

    public static UserBO poToBo(UserPO userPO) {
        if (userPO == null) {
            return null;
        }

        UserBO userBO = new UserBO();

        BeanUtils.copyProperties(userPO, userBO);

        return userBO;
    }
}
