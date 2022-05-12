package com.example.FamilyCareInventory.service;

import com.example.FamilyCareInventory.dao.OrderJdbcTemp;
import com.example.FamilyCareInventory.model.Order;
import com.example.FamilyCareInventory.model.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class OrderService {
    private final OrderJdbcTemp orderJdbcTemp;
    private final TransService transService;

    @Autowired
    public OrderService(OrderJdbcTemp orderJdbcTemp, TransService transService) {
        this.orderJdbcTemp = orderJdbcTemp;
        this.transService = transService;
    }

    public int addOrder(Order order) {
        ArrayList<OrderLine> OrderLines = order.getProducts();
        int orderId = orderJdbcTemp.addOrder(order);
        int orderLineId = 1;
        String method = order.getMethod();
        try {
            for (OrderLine Orderline : OrderLines) {
                Orderline.setOrderId(orderId);
                Orderline.setOrderlineId(orderLineId);
                orderJdbcTemp.addOrderLine(Orderline);
                orderLineId++;
            }
            if (method.equals("etransfer")) {
                transService.addTrans(order.getName(), order.getTrans_time(), order.getTotal(), 0, order.getNote());
            } else {
                transService.addTrans(order.getName(), order.getTrans_time(), 0, order.getTotal(), order.getNote());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderId;
    }

    public ArrayList<Order> getAllOrder() {
        return orderJdbcTemp.getAllOrder();
    }

    public ArrayList<OrderLine> getOrderLine(int OrderId) {
        return orderJdbcTemp.getOrderLine(OrderId);
    }

    public void deleteOrder(int orderId) {
        Order order = orderJdbcTemp.getOrderByOrderId(orderId);
        String method = order.getMethod();
        try {
            orderJdbcTemp.deleteOrder(orderId);
            if (method.equals("电子支付")) {
                transService.addTrans("返还款：" + order.getName(), new Date(), -1 * order.getTotal(), 0, order.getNote());
            } else {
                transService.addTrans("返还款：" + order.getName(), new Date(), 0, -1 * order.getTotal(), order.getNote());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
