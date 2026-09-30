package co.istad.rathpanha.ecommerce.order.persistence.adapter;

import co.istad.rathpanha.ecommerce.order.domain.entity.Order;
import co.istad.rathpanha.ecommerce.order.domain.port.output.OrderRepository;
import co.istad.rathpanha.ecommerce.order.persistence.entity.OrderEntity;
import co.istad.rathpanha.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import co.istad.rathpanha.ecommerce.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {
  private final OrderJpaRepository orderJpaRepository;
  private final OrderPersistenceMapper orderPersistenceMapper;

  @Override
  public Order saveOrder(Order order) {
    // Map Order to OrderEntity
    OrderEntity orderEntity = orderPersistenceMapper.orderToOrderEntity(order);

    orderEntity.getOrderAddress().setOrder(orderEntity);
    orderEntity.getItems()
        .forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));

    // Save into database
    OrderEntity savedOrderEntity = orderJpaRepository.save(orderEntity);
    // Map OrderEntity to Order
    return orderPersistenceMapper.orderEntityToOrder(savedOrderEntity);
  }
}
