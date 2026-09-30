package co.istad.rathpanha.ecommerce.order.restapi.mapper;

import co.istad.rathpanha.ecommerce.order.domain.dto.CreateOrderCommand;
import co.istad.rathpanha.ecommerce.order.domain.dto.CreateOrderResult;
import co.istad.rathpanha.ecommerce.order.restapi.dto.OrderCreateRequest;
import co.istad.rathpanha.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {
  // Source = OrderCreateRequest
  // Target = OrderCreateCommand
  @Mapping(source = "orderAddress", target = "deliveryAddress")
  CreateOrderCommand orderCreateRequestToCreateOrderCommand(OrderCreateRequest orderCreateRequest);

  OrderCreateResponse createOrderResultToCreateOrderResponse(CreateOrderResult createOrderResult);
}
