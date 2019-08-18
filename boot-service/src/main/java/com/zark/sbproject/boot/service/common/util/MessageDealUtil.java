package com.zark.sbproject.boot.service.common.util;

import com.zark.sbproject.boot.dao.user.entity.MessageDealPO;
import com.zark.sbproject.boot.service.common.bo.MessageDealBO;
import org.springframework.beans.BeanUtils;

/**
 * @author zark
 * @date 2019-08-18
 */
public class MessageDealUtil {

    public static MessageDealBO poToBo(MessageDealPO messageDealPO) {
        if (messageDealPO == null) {
            return null;
        }

        MessageDealBO messageDealBO = new MessageDealBO();

        BeanUtils.copyProperties(messageDealPO, messageDealBO);

        return messageDealBO;
    }

    public static MessageDealPO boToPo(MessageDealBO messageDealBO) {
        if (messageDealBO == null) {
            return null;
        }

        MessageDealPO messageDealPO = new MessageDealPO();

        BeanUtils.copyProperties(messageDealBO, messageDealPO);

        return messageDealPO;
    }


}
