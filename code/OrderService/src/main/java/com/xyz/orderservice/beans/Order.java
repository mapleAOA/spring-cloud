package com.xyz.orderservice.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private String userId;
    private String orderId; // 唯一标识
    private String status;  // 订单状态
    private List<OrderItem> orderItems;
    private String expectedDeliveryDate;
    private String creationTime; // 订单创建时间
    private String updateTime;

}
