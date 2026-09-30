package co.istad.rathpanha.ecommerce.order.domain.service;

import co.istad.rathpanha.ecommerce.order.domain.entity.Business;
import co.istad.rathpanha.ecommerce.order.domain.entity.Order;
import co.istad.rathpanha.ecommerce.order.domain.event.OrderCancelledEvent;
import co.istad.rathpanha.ecommerce.order.domain.event.OrderCreatedEvent;
import co.istad.rathpanha.ecommerce.order.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {
  OrderCreatedEvent validateAndInitiateOrder(Order order, Business business);
  OrderPaidEvent payOrder(Order order);
  void approveOrder(Order order);
  OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);
  void cancelOrder(Order order, List<String> failureMessages);
}
