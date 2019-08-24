package com.zark.sbproject.boot.dao.common.mapper;

import com.zark.sbproject.boot.dao.common.entity.MessageDealPO;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDealPOMapperExt extends MessageDealPOMapper {
    MessageDealPO lockByMessageId(String messageId);
}