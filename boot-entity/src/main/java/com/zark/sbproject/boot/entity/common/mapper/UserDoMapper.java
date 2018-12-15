package com.zark.sbproject.boot.entity.common.mapper;

import com.zark.sbproject.boot.entity.common.module.UserDo;
import com.zark.sbproject.boot.entity.common.module.UserDoExample;
import java.util.List;

public interface UserDoMapper {
    /**
     *
     * @mbggenerated
     */
    int countByExample(UserDoExample example);

    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(UserDo record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(UserDo record);

    /**
     *
     * @mbggenerated
     */
    List<UserDo> selectByExample(UserDoExample example);

    /**
     *
     * @mbggenerated
     */
    UserDo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserDo record);
}