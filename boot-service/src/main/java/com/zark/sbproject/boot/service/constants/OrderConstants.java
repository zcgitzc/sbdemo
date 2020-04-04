package com.zark.sbproject.boot.service.constants;

/**
 * @author wb-zc189961
 * @date 2020-03-24
 */
public interface OrderConstants {

    String ORDER_STATE_MACHINE_ID = "orderStateMachineId";

    enum OrderStatus {

        /**
         * 待支付
         */
        WAIT_PAYMENT,

        /**
         * 待发货
         */
        WAIT_DELIVER,

        /**
         * 待收货
         */
        WAIT_RECEIVE,

        /**
         * 订单结束
         */
        FINISH;

    }


    enum OrderStatusEvent {
        /**
         * 支付
         */
        PAYED,

        /**
         * 发货
         */
        DELIVERY,

        /**
         * 确认收货
         */
        RECEIVED;
    }


}
