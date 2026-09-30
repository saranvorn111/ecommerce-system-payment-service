package co.istad.rathpanha.ecommerce.order.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemIdEntity {
  private Integer id;
  private OrderEntity order;
}
