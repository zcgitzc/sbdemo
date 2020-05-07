package com.zark.sbproject.boot.service.common.service.impl;

import com.zark.sbproject.boot.service.common.service.TransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;


/**
 * 解决调用本类业务方法，没有事务支持
 *
 * @author zark
 */
@Slf4j
@Component
public class TransactionalServiceImpl implements TransactionalService {


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void executeWithNewTransactional(Execute execute) {
        execute.doExecute();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public <T> T executeWithNewTransactional(ExecuteWithReturn<T> execute) {
        return execute.doExecute();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void executeWithTransactional(Execute execute) {
        execute.doExecute();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public <T> T executeWithTransactional(ExecuteWithReturn<T> execute) {
        return execute.doExecute();
    }

    @Override
    public void executeAfterCommit(final Execute execute) {
        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            log.debug("Execute immediately without active Synchronization");
            execute.doExecute();
            return;
        }
        log.debug("Register to transactionSynchronizationManager");
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {

            @Override
            public void afterCommit() {
                log.debug("Execute after commit");
                execute.doExecute();
            }
        });
    }

    @Override
    public void executeBeforeCommit(final Execute execute) {
        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            log.debug("Execute immediately without active Synchronization");
            execute.doExecute();
            return;
        }
        log.debug("Register to transactionSynchronizationManager");
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void beforeCommit(boolean readOnly) {
                log.debug("Execute before commit");
                execute.doExecute();
            }
        });
    }
}