package com.zark.sbproject.boot.service.common.service;

public interface LockLocalService {

    boolean tryLock(String lockKey,long timeout,long perTime);

    boolean tryLock(String lockKey);

    void release(String lockKey);
}
