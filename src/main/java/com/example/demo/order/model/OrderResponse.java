package com.example.demo.order.model;

public class OrderResponse {
    private int statusCode;
    private String message;
    private Order order;

    public OrderResponse() {}

    public OrderResponse(int statusCode, String message, Order order) {
        this.statusCode = statusCode;
        this.message = message;
        this.order = order;
    }

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}
