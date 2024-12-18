package com.xyz.orderservice.util;

import com.xyz.orderservice.beans.Order;
import com.xyz.orderservice.beans.OrderItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class datas {
    public static Map<String, Order> orderDatabase = new HashMap<>();
    static {
        // 预先放入一些订单数据
        List<OrderItem> orderItems1 = new ArrayList<>();
        orderItems1.add(new OrderItem("product001", 2));
        orderItems1.add(new OrderItem("product002", 1));

        List<OrderItem> orderItems2 = new ArrayList<>();
        orderItems2.add(new OrderItem("product003", 5));
        orderItems2.add(new OrderItem("product004", 3));

        List<OrderItem> orderItems3 = new ArrayList<>();
        orderItems3.add(new OrderItem("product005", 1));

        // 初始化订单数据
        Order order1 = new Order("user123", "user1232024-12-05","创建中",orderItems1,"2024-1220","2024-12-05", "2024-12-08");
        Order order2 = new Order("user456", "user4562024-12-06","已完成",orderItems2, "2024-12-21", "2024-12-06", "2024-12-01");
        Order order3 = new Order("user789","user7892024-12-07","创建失败",orderItems3,  "2024-12-19", "N/A", "2024-12-07");
//        Order order4 = new Order("order001","user7892024-12-07","创建失败",orderItems3,  "2024-12-19", "N/A", "2024-12-07");

        orderDatabase.put(order1.getOrderId(), order1);
        orderDatabase.put(order2.getOrderId(), order2);
        orderDatabase.put(order3.getOrderId(), order3);
//        orderDatabase.put(order4.getOrderId(), order4);
    }
}
