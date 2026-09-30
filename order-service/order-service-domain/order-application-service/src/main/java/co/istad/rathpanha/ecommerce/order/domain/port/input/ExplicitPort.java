package co.istad.rathpanha.ecommerce.order.domain.port.input;

import co.istad.rathpanha.ecommerce.order.domain.dto.CreateOrderCommand;

public interface ExplicitPort {
  void execute(CreateOrderCommand createOrderCommand);
}
