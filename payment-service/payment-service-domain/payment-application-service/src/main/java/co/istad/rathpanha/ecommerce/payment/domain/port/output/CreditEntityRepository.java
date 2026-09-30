package co.istad.rathpanha.ecommerce.payment.domain.port.output;

import co.istad.rathpanha.ecommerce.domain.valueobject.CustomerId;
import co.istad.rathpanha.ecommerce.payment.domain.entity.CreditEntry;

public interface CreditEntityRepository  {
    CreditEntry findByCustomerId(CustomerId customerId);

    CreditEntry save(CreditEntry creditEntry);
}
