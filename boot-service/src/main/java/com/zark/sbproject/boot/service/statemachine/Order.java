package com.zark.sbproject.boot.service.statemachine;

import com.zark.sbproject.boot.service.constants.OrderConstants;
import lombok.Data;

/**
 * @author wb-zc189961
 * @date 2020-03-24
 */
@Data
public class Order {
    private Integer id;

    private OrderConstants.OrderStatus status;

}
