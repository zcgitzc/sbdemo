package com.zark.sbproject.boot.dao.user.mapper;

import com.zark.sbproject.boot.dao.user.entity.MessageDealPO;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDealPOMapperExt extends MessageDealPOMapper {
    MessageDealPO lockByMessageId(String messageId);
}