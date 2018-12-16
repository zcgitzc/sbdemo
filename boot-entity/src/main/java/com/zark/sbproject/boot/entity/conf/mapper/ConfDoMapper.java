package com.zark.sbproject.boot.entity.conf.mapper;

import com.zark.sbproject.boot.entity.conf.module.ConfDo;
import com.zark.sbproject.boot.entity.conf.module.ConfDoExample;
import java.util.List;

public interface ConfDoMapper {
    /**
     *
     * @mbggenerated
     */
    int countByExample(ConfDoExample example);

    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(ConfDo record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ConfDo record);

    /**
     *
     * @mbggenerated
     */
    List<ConfDo> selectByExample(ConfDoExample example);

    /**
     *
     * @mbggenerated
     */
    ConfDo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ConfDo record);
}