package com.zark.sbproject.boot.service.common.service;

import com.zark.sbproject.boot.service.common.bo.MessageDealBO;

/**
 * @author zark
 * @date 2019-08-18 23:08:07
 */
public interface MessageDealLocalService {

    MessageDealBO lockByDestination(String messageId);

    int insert(MessageDealBO messageDealBO);

    int updateById(MessageDealBO messageDealBO);
}
