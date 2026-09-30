package co.istad.rathpanha.ecommerce.restapi.exception;

import co.istad.rathpanha.ecommerce.restapi.dto.FieldErrorResponse;
import co.istad.rathpanha.ecommerce.restapi.dto.RestApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private List<FieldErrorResponse> extractFieldError(List<FieldError> errors) {
    return errors.stream().map(error -> new FieldErrorResponse(
        error.getField(),
        error.getCode(),
        error.getDefaultMessage()
    )).toList();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public RestApiErrorResponse<?> handleException(MethodArgumentNotValidException e) {
    return RestApiErrorResponse.builder()
        .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message("Data validation failed")
        .detail(extractFieldError(e.getFieldErrors()))
        .build();
  }
}
