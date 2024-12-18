package com.xyz.orderservice.service.impl;

import com.xyz.orderservice.beans.Order;
import com.xyz.orderservice.beans.OrderItem;
import com.xyz.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.xyz.orderservice.util.datas.orderDatabase;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(String userid, List<OrderItem> items, String expectedDate){
        Order order=Order.builder()
                .userId(userid)
                .orderItems(items)
                .creationTime(java.time.LocalDateTime.now().toString())
                .expectedDeliveryDate(expectedDate)
                .build();
        //更具当前状况生成其他需要的数据，比如orderid，最后存入order
        orderDatabase.put(userid,order);
        return order;
    }

    @Override
    public Order findById(String id) {
        return orderDatabase.get(id);
    }
    @Override
    public Order updateOrder(String orderId, String userId, List<OrderItem> items, String expectedDate) {
        Order cur = findById(orderId);
        if (cur != null && cur.getUserId().equals(userId)) {
            cur.setOrderItems(items);
            cur.setExpectedDeliveryDate(expectedDate);
            cur.setUpdateTime(java.time.LocalDateTime.now().toString());
        } else {
            throw new IllegalArgumentException("Order not found or unauthorized access.");
        }
        return cur;
    }

    @Override
    public String test() {
        return "test";
    }

    @Override
    public List getAllOrder(String expectedDeliveryDate,String creationTime,String updateTime) {
        List<Order> orders=new ArrayList<>(orderDatabase.values());
        //这里操作从中取出符合条件的订单
        return orders;
    }
}
