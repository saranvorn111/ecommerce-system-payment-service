package co.istad.rathpanha.ecommerce.order.persistence.repository;

import co.istad.rathpanha.ecommerce.order.persistence.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BusinessJpaRepository extends JpaRepository<BusinessEntity, UUID> {
  // Find by business ID and collection of product ID
  List<BusinessEntity> findByBusinessIdAndProductIdIn(
    UUID businessId,
    List<UUID> productIds
  );
}
