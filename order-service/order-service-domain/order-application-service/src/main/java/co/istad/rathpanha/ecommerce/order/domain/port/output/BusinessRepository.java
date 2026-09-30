package co.istad.rathpanha.ecommerce.order.domain.port.output;

import co.istad.rathpanha.ecommerce.order.domain.entity.Business;

import java.util.Optional;
import java.util.UUID;

public interface BusinessRepository {
  Optional<Business> findBusiness(Business business);
}
