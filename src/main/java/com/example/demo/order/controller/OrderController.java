package com.example.demo.order.controller;

import com.example.demo.order.model.Order;
import com.example.demo.order.model.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot REST Controller - Lambda Handler와 동일한 로직을 로컬에서 테스트 가능
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody Order order) {
        log.info("=== 주문 접수 시작 ===");
        log.info("주문 ID: {}", order.getOrderId());
        log.info("고객명: {}", order.getCustomerName());
        log.info("상품명: {}", order.getProductName());
        log.info("수량: {}", order.getQuantity());
        log.info("총 금액: {}", order.getTotalPrice());
        log.info("=== 주문 접수 완료 ===");

        order.setStatus("RECEIVED");

        OrderResponse response = new OrderResponse();
        response.setStatusCode(200);
        response.setMessage("주문이 정상적으로 접수되었습니다.");
        response.setOrder(order);

        return ResponseEntity.ok(response);
    }
}
