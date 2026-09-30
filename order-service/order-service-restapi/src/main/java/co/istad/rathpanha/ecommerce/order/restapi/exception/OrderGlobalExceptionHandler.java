package co.istad.rathpanha.ecommerce.order.restapi.exception;

import co.istad.rathpanha.ecommerce.order.domain.exception.OrderDomainException;
import co.istad.rathpanha.ecommerce.persistence.business.exception.BusinessPersistenceException;
import co.istad.rathpanha.ecommerce.restapi.dto.RestApiErrorResponse;
import co.istad.rathpanha.ecommerce.restapi.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {
  @ExceptionHandler(OrderDomainException.class)
  public RestApiErrorResponse<?> handleOrderDomainException(OrderDomainException e) {
    return RestApiErrorResponse.builder()
        .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(e.getMessage())
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BusinessPersistenceException.class)
  public RestApiErrorResponse<?> handleOrderPersistenceException(BusinessPersistenceException e) {
    return RestApiErrorResponse.builder()
        .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(e.getMessage())
        .build();
  }
}
