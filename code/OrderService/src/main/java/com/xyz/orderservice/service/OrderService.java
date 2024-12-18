package com.xyz.orderservice.service;


import com.xyz.orderservice.beans.Order;
import com.xyz.orderservice.beans.OrderItem;

import java.util.List;

public interface OrderService {
    public Order createOrder(String userid,List<OrderItem> items,String expectedDate);
    public Order findById(String id);
    public Order updateOrder(String orderId, String userId,List<OrderItem> items,String expectedDate);
    public String test();
    public List getAllOrder(String expectedDeliveryDate,String creationTime,String updateTime);
}
