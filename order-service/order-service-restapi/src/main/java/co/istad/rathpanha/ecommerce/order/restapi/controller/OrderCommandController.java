package co.istad.rathpanha.ecommerce.order.restapi.controller;

import co.istad.rathpanha.ecommerce.order.domain.dto.CreateOrderCommand;
import co.istad.rathpanha.ecommerce.order.domain.dto.CreateOrderResult;
import co.istad.rathpanha.ecommerce.order.domain.usecase.CreateOrderUseCase;
import co.istad.rathpanha.ecommerce.order.restapi.dto.OrderCreateRequest;
import co.istad.rathpanha.ecommerce.order.restapi.dto.OrderCreateResponse;
import co.istad.rathpanha.ecommerce.order.restapi.mapper.OrderWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderCommandController {

  // Declare required dependency
  private final CreateOrderUseCase createOrderUseCase;
  private final OrderWebMapper orderWebMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderCreateResponse createOrder(
     @Valid @RequestBody OrderCreateRequest orderCreateRequest
  ) {
    // Mapping login
    CreateOrderCommand createOrderCommand = orderWebMapper
        .orderCreateRequestToCreateOrderCommand(orderCreateRequest);

    // UseCase logic
    CreateOrderResult createOrderResult = createOrderUseCase.execute(createOrderCommand);

    // Mapping logic
    return orderWebMapper.createOrderResultToCreateOrderResponse(createOrderResult);
  }
}
