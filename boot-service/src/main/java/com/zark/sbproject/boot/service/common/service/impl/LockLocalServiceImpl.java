package com.zark.sbproject.boot.service.common.service.impl;

import com.zark.sbproject.boot.common.util.BasePOUtil;
import com.zark.sbproject.boot.dao.common.entity.LockPO;
import com.zark.sbproject.boot.dao.common.mapper.LockPOMapperExt;
import com.zark.sbproject.boot.service.common.service.LockLocalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zark
 * @description
 * @date 2019-08-24 01:48
 */
@Service
@Slf4j
public class LockLocalServiceImpl implements LockLocalService {


    @Resource
    private LockPOMapperExt lockPOMapperExt;

    private long expireTimePeriod = 15 * 60 * 1000;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public boolean tryLock(String lockKey, long timeout, long perTime) {
        long deadline = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < deadline) {
            if (tryLock(lockKey)) {
                return true;
            }
            try {
                Thread.sleep(perTime);
            } catch (InterruptedException e) {
                log.error("tryLock failed, lockKey:{} timeout:{}", lockKey, timeout, e);
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public boolean tryLock(String lockKey) {
        try {
            LockPO appLockPO = buildLockPO(lockKey, expireTimePeriod);
            lockPOMapperExt.insertSelective(appLockPO);
        } catch (DuplicateKeyException e) {
            //加锁失败，说明锁被别的机器获取，判断别的机器锁是否超时
            try {
                LockPO dbSparkLockPO = lockPOMapperExt.selectByLockKey(lockKey);
                if (dbSparkLockPO.getExpireTime().getTime() < System.currentTimeMillis()) {
                    // TODO: 2019-06-12 加上上一个版本时间戳删除
                    release(lockKey);
                    LockPO sparkLockPO = buildLockPO(lockKey, expireTimePeriod);
                    lockPOMapperExt.insertSelective(sparkLockPO);
                    return true;
                }
                return false;

            } catch (Exception exception) {
                log.error("加锁失败", exception);
                return false;
            }

        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void release(String lockKey) {
        lockPOMapperExt.deleteByLockKey(lockKey);
    }

    private LockPO buildLockPO(String lockKey, long expireTimePeriod) {
        LockPO sparkLockPO = new LockPO();
        sparkLockPO.setLockKey(lockKey);
        long expireTime = System.currentTimeMillis() + expireTimePeriod;
        sparkLockPO.setExpireTime(new Date(expireTime));
        BasePOUtil.buildCreatePo(sparkLockPO);
        return sparkLockPO;
    }
    
}
