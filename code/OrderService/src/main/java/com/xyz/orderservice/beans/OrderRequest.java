package com.xyz.orderservice.beans;

import com.xyz.orderservice.util.JwtUtil;

import java.util.List;

public class OrderRequest {
    private List<OrderItem> orderItems;
    private String expectedDeliveryDate; // YYYY-MM-DD
    private String orderId;
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getOrderId() {
        return orderId;
    }
}
