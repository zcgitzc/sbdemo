package com.zark.sbproject.boot.service.common.bo;

import com.zark.sbproject.boot.service.common.constant.GraphStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zark
 * @description
 * @date 2019-08-25 19:05
 */
@Data
@AllArgsConstructor
public class GraphInstanceBO {

    private GraphStatus status;
}
