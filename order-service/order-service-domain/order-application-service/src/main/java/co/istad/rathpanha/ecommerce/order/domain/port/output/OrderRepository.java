package co.istad.rathpanha.ecommerce.order.domain.port.output;

import co.istad.rathpanha.ecommerce.order.domain.entity.Order;

public interface OrderRepository {
  Order saveOrder(Order order);
}
