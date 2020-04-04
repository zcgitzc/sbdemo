package com.zark.sbproject.boot.service.statemachine.service;


import com.zark.sbproject.boot.service.statemachine.Order;

import java.util.Map;

/**
 * @author wb-zc189961
 * @date 2020-03-24
 */
public interface OrderService {

    Order create();

    Order pay(int id);

    Order deliver(int id);

    Order receive(int id);

    Map<Integer, Order> getOrders();
}
