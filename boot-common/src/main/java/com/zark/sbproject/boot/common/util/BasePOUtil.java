package com.zark.sbproject.boot.common.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Date;

/**
* @description
* @author zark
* @date 2019-08-24 02:00
*/
public class BasePOUtil {

    private static final String CREATE_BY = "creator";
    private static final String UPDATE_BY = "modifier";
    private static final String CREATE_TIME = "gmt_create";
    private static final String UPDATE_TIME = "gmt_modified";
    private static final String IS_DELETED = "isDeleted";
    private static final String SYS_OPERATOR = "system";


    public static void buildCreatePo(Object obj) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
        // 设置创建时间和修改时间
        if (beanWrapper.isWritableProperty(CREATE_TIME)) {
            beanWrapper.setPropertyValue(CREATE_TIME, new Date());
        }
        if (beanWrapper.isWritableProperty(UPDATE_TIME)) {
            beanWrapper.setPropertyValue(UPDATE_TIME, new Date());
        }
        // 设置创建人和修改人
        if (beanWrapper.isWritableProperty(CREATE_BY) && beanWrapper.getPropertyValue(CREATE_BY) == null) {
            beanWrapper.setPropertyValue(CREATE_BY, SYS_OPERATOR);
        }
        if (beanWrapper.isWritableProperty(UPDATE_BY) && beanWrapper.getPropertyValue(UPDATE_BY) == null) {
            beanWrapper.setPropertyValue(UPDATE_BY, SYS_OPERATOR);
        }
        // isDeleted
        if (beanWrapper.isWritableProperty(IS_DELETED)) {
            beanWrapper.setPropertyValue(IS_DELETED, "n");
        }
    }


    public static void buildUpdatePo(Object obj) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
        // 设置修改时间
        if (beanWrapper.isWritableProperty(UPDATE_TIME)) {
            beanWrapper.setPropertyValue(UPDATE_TIME, new Date());
        }
        // 设置修改人
        if (beanWrapper.isWritableProperty(UPDATE_BY) && beanWrapper.getPropertyValue(UPDATE_BY) == null) {
            beanWrapper.setPropertyValue(UPDATE_BY, SYS_OPERATOR);
        }
        // isDeleted
        if (beanWrapper.isWritableProperty(IS_DELETED) && beanWrapper.getPropertyValue(IS_DELETED) == null) {
            beanWrapper.setPropertyValue(IS_DELETED, "n");
        }
    }

}
