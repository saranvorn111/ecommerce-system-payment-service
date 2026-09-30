package co.istad.rathpanha.ecommerce.order.persistence.adapter;

import co.istad.rathpanha.ecommerce.order.domain.entity.Customer;
import co.istad.rathpanha.ecommerce.order.domain.port.output.CustomerRepository;
import co.istad.rathpanha.ecommerce.order.persistence.mapper.CustomerPersistenceMapper;
import co.istad.rathpanha.ecommerce.order.persistence.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {
  private final CustomerJpaRepository customerJpaRepository;
  private final CustomerPersistenceMapper customerPersistenceMapper;

  @Override
  public Optional<Customer> findCustomer(UUID customerId) {
    return customerJpaRepository.findById(customerId).map(customerPersistenceMapper::customerEntityToCustomer);
  }
}
