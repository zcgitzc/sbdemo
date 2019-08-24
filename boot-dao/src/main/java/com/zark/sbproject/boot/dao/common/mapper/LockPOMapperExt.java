package com.zark.sbproject.boot.dao.common.mapper;

import com.zark.sbproject.boot.dao.common.entity.LockPO;
import org.springframework.stereotype.Repository;

@Repository
public interface LockPOMapperExt extends LockPOMapper {
    LockPO selectByLockKey(String lockKey);

    void deleteByLockKey(String lockKey);
}