package com.example.demo.order.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.demo.order.model.Order;
import com.example.demo.order.model.OrderResponse;

/**
 * AWS Lambda Handler - 주문 데이터를 처리하는 서버리스 함수
 *
 * Spring Boot의 @RestController 역할을 Lambda에서 수행합니다.
 * API Gateway로부터 JSON 형태의 Order 데이터를 받아 처리합니다.
 */
public class OrderHandler implements RequestHandler<Order, OrderResponse> {

    @Override
    public OrderResponse handleRequest(Order order, Context context) {
        LambdaLogger logger = context.getLogger();

        // 주문 데이터 로깅
        logger.log("=== 주문 접수 시작 ===\n");
        logger.log("주문 ID: " + order.getOrderId() + "\n");
        logger.log("고객명: " + order.getCustomerName() + "\n");
        logger.log("상품명: " + order.getProductName() + "\n");
        logger.log("수량: " + order.getQuantity() + "\n");
        logger.log("총 금액: " + order.getTotalPrice() + "\n");
        logger.log("=== 주문 접수 완료 ===\n");

        // 주문 상태를 RECEIVED로 설정
        order.setStatus("RECEIVED");

        OrderResponse response = new OrderResponse();
        response.setStatusCode(200);
        response.setMessage("주문이 정상적으로 접수되었습니다.");
        response.setOrder(order);

        return response;
    }
}
