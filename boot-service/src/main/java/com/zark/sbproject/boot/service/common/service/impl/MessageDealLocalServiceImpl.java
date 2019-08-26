package com.zark.sbproject.boot.service.common.service.impl;

import com.zark.sbproject.boot.dao.common.entity.MessageDealPO;
import com.zark.sbproject.boot.dao.common.mapper.MessageDealPOMapperExt;
import com.zark.sbproject.boot.service.common.bo.MessageDealBO;
import com.zark.sbproject.boot.service.common.service.MessageDealLocalService;
import com.zark.sbproject.boot.service.common.util.MessageDealUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zark
 * @date 2019-08-18
 */
@Service
public class MessageDealLocalServiceImpl implements MessageDealLocalService {

    @Resource
    private MessageDealPOMapperExt messageDealPOMapperExt;

    @Override
    public MessageDealBO lockByDestination(String messageId) {
        MessageDealPO messageDealPO = messageDealPOMapperExt.lockByMessageId(messageId);

        return MessageDealUtil.poToBo(messageDealPO);
    }

    @Override
    public int insert(MessageDealBO messageDealBO) {
        MessageDealPO messageDealPO = MessageDealUtil.boToPo(messageDealBO);
        int row = messageDealPOMapperExt.insertSelective(messageDealPO);
        //特别注意：容易忘记回填ID
        messageDealBO.setId(messageDealPO.getId());
        return row;
    }

    @Override
    public int updateById(MessageDealBO messageDealBO) {
        MessageDealPO messageDealPO = MessageDealUtil.boToPo(messageDealBO);
        return messageDealPOMapperExt.updateByPrimaryKeySelective(messageDealPO);
    }


}
