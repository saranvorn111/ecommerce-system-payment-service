package co.istad.rathpanha.ecommerce.order.domain.exception;

import co.istad.rathpanha.ecommerce.domain.exception.DomainException;

public class OrderDomainException extends DomainException {
  public OrderDomainException(String message) {
    super(message);
  }

  public OrderDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
