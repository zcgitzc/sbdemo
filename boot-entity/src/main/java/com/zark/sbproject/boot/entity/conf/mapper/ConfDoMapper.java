package com.zark.sbproject.boot.entity.conf.mapper;

import com.zark.sbproject.boot.entity.conf.module.ConfDo;
import com.zark.sbproject.boot.entity.conf.module.ConfDoExample;
import java.util.List;

public interface ConfDoMapper {
    /**
     *
     * @mbggenerated 2018-12-15
     */
    int countByExample(ConfDoExample example);

    /**
     *
     * @mbggenerated 2018-12-15
     */
    int deleteByPrimaryKey(ConfDo record);

    /**
     *
     * @mbggenerated 2018-12-15
     */
    int insertSelective(ConfDo record);

    /**
     *
     * @mbggenerated 2018-12-15
     */
    List<ConfDo> selectByExample(ConfDoExample example);

    /**
     *
     * @mbggenerated 2018-12-15
     */
    ConfDo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-12-15
     */
    int updateByPrimaryKeySelective(ConfDo record);
}