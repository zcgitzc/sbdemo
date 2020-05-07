package com.zark.sbproject.boot.service.common.service;

public interface TransactionalService {

    void executeWithNewTransactional(Execute execute);

    <T>T executeWithNewTransactional(ExecuteWithReturn<T> execute);

    void executeWithTransactional(Execute execute);

    <T>T executeWithTransactional(ExecuteWithReturn<T> execute);

    void executeBeforeCommit(Execute execute);

    void executeAfterCommit(Execute execute);

    interface Execute{
        void doExecute();
    }

    interface ExecuteWithReturn <T>{
        T doExecute();
    }
}